package cn.jiangaifeng.validate;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 510L;
    private final Object principal;

    public SmsCodeAuthenticationToken(Object principal) {
        super((Collection)null);
        this.principal = principal;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

}
