package com.ugamdemo.core.Replication;


        import com.day.cq.commons.date.DateUtil;
        import com.day.cq.replication.Preprocessor;
        import com.day.cq.replication.ReplicationAction;
        import com.day.cq.replication.ReplicationActionType;
        import com.day.cq.replication.ReplicationException;
        import com.day.cq.replication.ReplicationOptions;
        import com.ugamdemo.core.models.Impl.ResolveUtils;
        import org.apache.sling.api.resource.Resource;
        import org.apache.sling.api.resource.ResourceResolver;
        import org.apache.sling.api.resource.ResourceResolverFactory;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import javax.jcr.Node;
        import javax.jcr.Property;
        import javax.jcr.Session;
        import java.util.Calendar;
        import java.util.Date;
@Component(
        immediate = true
)
public class SampleReplicationPreprocessor implements Preprocessor {
    private static final Logger log = LoggerFactory.getLogger(SampleReplicationPreprocessor.class);
    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    public void preprocess(final ReplicationAction replicationAction,
                           final ReplicationOptions replicationOptions) throws ReplicationException {

        if (replicationAction == null || !ReplicationActionType.ACTIVATE.equals(replicationAction.getType())) {
            // Do nothing
            return;
        }
        // Get the path of the replication payload
        final String path = replicationAction.getPath();
        // ResourceResolver resourceResolver = null;
        try {
            if(path.equals("/content/ugamdemo/us/en/demoabc")) {
                ResourceResolver serviceResourceResolver = ResolveUtils.newResolver(resourceResolverFactory);
                Session session = serviceResourceResolver.adaptTo(Session.class);
                Resource resource = serviceResourceResolver.getResource("/content/ugamdemo/us/en/demoabc/jcr:content/root/container/container/schedulerdate");
                Node node = resource.adaptTo(Node.class);
                Property property = node.getProperty("time");

                if (property.getValue() != DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())) ) {
                    log.info("value of date before update" + property.getValue());
                    node.setProperty("time", DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())));
                    log.info("updated node time" + DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())));

                }
                session.save();
                session.logout();
            }

        }catch (Exception e) {
            // To prevent Replication from happening, throw a ReplicationException
            throw new ReplicationException(e);
        }
    }
}