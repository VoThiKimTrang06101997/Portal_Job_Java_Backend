package com.r2s.findInternship.common.util.oauth;
import com.r2s.findInternship.common.enumeration.EAuthenticationProvider;
import com.r2s.findInternship.exception.CustomException;

import java.util.Map;
public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes,String clientName) {
        if(registrationId.equalsIgnoreCase(EAuthenticationProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes,clientName);
        }
        if(registrationId.equalsIgnoreCase(EAuthenticationProvider.FACEBOOK.toString())) {
            return new GoogleOAuth2UserInfo(attributes,clientName);
        }else {
            throw new CustomException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
