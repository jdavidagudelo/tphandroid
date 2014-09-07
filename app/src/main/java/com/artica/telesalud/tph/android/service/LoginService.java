package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.LoginActivity;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.UserSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by interoperabilidad on 28/05/14.
 */
public class LoginService extends GetListAsyncService<String, Void, UserSpringDto>  {
    public LoginService(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected UserSpringDto doInBackground(String... params) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.getString(R.string.uri_base_rest_services));
            sb.append(activity.getString(R.string.uri_login));
            Resources resources = activity.getResources();
            String paramsKey[] = resources.getStringArray(R.array.uri_login_params);
            final String url = sb.toString();
            Map<String, String> map = new HashMap<String, String>();
            DateObjectMapper mapper = new DateObjectMapper();
            for (int i = 0; i < params.length; i++) {
                map.put(paramsKey[i], String.valueOf(params[i]));
            }
            /**RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            UserDto user = restTemplate.getForObject(url, UserDto.class, map);*/
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());

            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<UserSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, UserSpringDto.class, map);
            return entity.getBody();
        } catch (Exception e) {
            Log.e("TAG", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(UserSpringDto user) {
        if(user != null)
        {
            UserInSession.setInstance(new UserDto(user));

            if(activity != null && activity instanceof LoginActivity)
            {
                LoginActivity loginActivity = (LoginActivity)activity;
                loginActivity.setUser(new UserDto(user));
            }
        }
    }
}
