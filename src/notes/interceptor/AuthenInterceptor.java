package notes.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
//import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by APone on 2017/5/23.
 * 省份验证拦截器
 */
public class AuthenInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();

        if (session.get("user") == null) {
            //ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
            //actionSupport.addActionError("你尚未登录，请先登录");
            return Action.LOGIN;
        } else {
            return actionInvocation.invoke();
        }
    }
}
