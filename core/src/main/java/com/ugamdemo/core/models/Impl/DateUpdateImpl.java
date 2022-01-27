package com.ugamdemo.core.models.Impl;

        import com.day.cq.commons.date.DateUtil;
        import com.ugamdemo.core.config.SchedulerConfiguration;
        import com.ugamdemo.core.models.DateUpdate;
        import org.apache.sling.api.resource.*;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;
        import org.osgi.service.metatype.annotations.Designate;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import javax.jcr.*;
        import java.util.Calendar;

@Component(immediate = true,service = DateUpdate.class)
@Designate(ocd = SchedulerConfiguration.class)
public class DateUpdateImpl implements DateUpdate{

     String path ="/content/ugamdemo/us/en/demoabc/jcr:content/root/container/container/schedulerdate";

    static final Logger LOG = LoggerFactory.getLogger(DateUpdateImpl.class);
    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    public String updateDate( String path) {
        try{
            ResourceResolver serviceResourceResolver = ResolveUtils.newResolver(resourceResolverFactory);
            Session session = serviceResourceResolver.adaptTo(Session.class);
            Resource resource = serviceResourceResolver.getResource("/content/ugamdemo/us/en/demoabc/jcr:content/root/container/container/schedulerdate");
            Node node = resource.adaptTo(Node.class);
             node.setProperty("time2" , "hello there aem");
             node.setProperty("time", DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())));
            session.save();
            session.logout();

        } catch (Exception e){

        }

           return path;
    }

}
