import axios from 'axios'
import Auth from '@/services/Auth'
import store from '../store/'
const instance = axios.create({
  baseURL: `http://localhost:8080/`
})

/*
      config.headers['Content-type']= [
        'application/json'
      ].join(' ')
 */

instance.interceptors.request.use((config, next) => {

  if (JSON.parse(localStorage.getItem('vuex')).user.token) {
  //console.log(">>VFF "+JSON.parse(localStorage.getItem('vuex')).user.token+"------------"+ JSON.stringify(localStorage));
  config.headers['Authorization'] = [
    'Bearer', JSON.parse(localStorage.getItem('vuex')).user.token
  ].join(' ')

} else {
  //console.log(">>VFF2 "+JSON.parse(localStorage.getItem('vuex'))+"------------"+ JSON.stringify(localStorage));
  delete config.headers['Authorization']
}
//console.log("config >>>> "+JSON.parse(localStorage.getItem('vuex')).auth.isAuthenticated.roles+' --->' +JSON.parse(localStorage.getItem('vuex')).user+"---"+JSON.stringify(localStorage)+"");
return config;

}, function (error) {
  return Promise.reject(error);
});


/**
 * Attempts to re-authenticate against backend spring security if token expired
 */
instance.interceptors.response.use(function (response) {
  return response;
}, function (error) {
  const originalRequest = error.config;
  console.log('instance.interceptors error.response '+JSON.stringify(error.response))
  if (error.response.status === 401 && !originalRequest._retry) {
    originalRequest._retry = true;
    const authUser = JSON.parse(window.localStorage.getItem('vuex'));
    const rToken=authUser.auth.isAuthenticated.refresh_token;
    console.log('attempt to refresh token here -'+'http://localhost:8080/oauth/access_token?grant_type=refresh_token&refresh_token='+rToken);
    // console.log('r token '+authUser.auth.isAuthenticated.refresh_token+'_____________-'+JSON.stringify(authUser.auth.isAuthenticated))
    //, {grant_type:'refresh_token',refresh_token:tok}
    return axios.post('http://localhost:8080/oauth/access_token?grant_type=refresh_token&refresh_token='+rToken)
      .then(({data}) => {
          console.log('==got the following token back: '+ data.access_token+'___________________________________________'+authUser.user.token)
          //console.log("------------"+JSON.stringify(data)+' ')

          //window.localStorage.setItem('vuex.auth.isAuthenticated.refresh_token', data.refresh_token);

          //This updates underyling user token with returned data - by updating it here saves recalling this method
          //over and over again
          store.dispatch('user/setToken', data.access_token);

          const authUser1 = JSON.parse(window.localStorage.getItem('vuex'));
          //This is simply checking set data matches what came back in http request
          if (data.access_token===authUser1.user.token) {
            console.log('Great news the underlying user token has now been updated meaning the user has now been re-authenticated')
          }

          //This part updates underlying authorisation token of current request
          axios.defaults.headers.common['Authorization'] = 'Bearer ' + data.access_token;
          originalRequest.headers['Authorization'] = 'Bearer ' + data.access_token;
          return axios(originalRequest);
      });
  }
  return Promise.reject(error);
});

export default () => {
  return instance
}
