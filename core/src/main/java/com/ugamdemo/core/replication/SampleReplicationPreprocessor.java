package com.ugamdemo.core.replication;
import com.day.cq.commons.date.DateUtil;
import com.day.cq.replication.Preprocessor;
import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.ReplicationOptions;
import com.ugamdemo.core.models.DateUpdate;
import com.ugamdemo.core.models.Impl.ResolveUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;
import java.util.Calendar;

@Component(
        immediate = true
)
public class SampleReplicationPreprocessor implements Preprocessor {
    @Reference
    ResourceResolverFactory resourceResolverFactory;



    @Reference
    DateUpdate dateUpdate;

    @Override
    public void preprocess(final ReplicationAction replicationAction,
                           final ReplicationOptions replicationOptions) throws ReplicationException {

        if (replicationAction == null || !ReplicationActionType.ACTIVATE.equals(replicationAction.getType())) {

            return;
        }

        final String path = replicationAction.getPath();

        try(ResourceResolver serviceResourceResolver = ResolveUtils.newResolver(resourceResolverFactory)) {
            if(path.equals("/content/ugamdemo/us/en/demoabc")) {
                Session session = serviceResourceResolver.adaptTo(Session.class);
                Resource resource = serviceResourceResolver.getResource("/content/ugamdemo/us/en/demoabc/jcr:content/root/container/container/schedulerdate");
                Node node = resource.adaptTo(Node.class);
                Property property = node.getProperty("time");
                if (property.getValue() != DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())) ) {
                    dateUpdate.updateDate("/content/ugamdemo/us/en/demoabc/jcr:content/root/container/container/schedulerdate");
                }
                session.save();
                session.logout();
            }

        }catch (Exception e) {
            throw new ReplicationException(e);
        }
    }
}

