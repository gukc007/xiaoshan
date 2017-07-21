package com.xiaoshan.common.helper;

import com.xiaoshan.common.BusinessExceptionBase;
import com.xiaoshan.datacontract.RequestUserDto;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by admin on 2017/7/12.
 */
public class CurrentRequestHelper extends AbstractAuthenticationToken{

    private RequestUserDto dto;

    public CurrentRequestHelper(RequestUserDto dto) {
        super(Collections.emptyList());
        this.dto = dto;
    }

    public CurrentRequestHelper(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getPrincipal() {
        return dto;
    }

    public static RequestUserDto getCurrentUser() throws Exception{
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return null;
        } else {
            return (RequestUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
    }

    public static Long getCurrentUserId() throws Exception {
        RequestUserDto dto = getCurrentUser();
        if (dto == null) {
            throw new BusinessExceptionBase(BusinessExceptionBase.EnumExceptionType.UserNoRight);
        }
        return dto.getId();
    }

}
