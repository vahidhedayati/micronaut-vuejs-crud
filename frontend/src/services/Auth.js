import Api from '@/services/Api'

export default {
    checkExpiredToken: (response, request) => {
        console.log(request)
        return new Promise((resolve, reject) => {
            console.log(this)
            //If token is expired, refresh token, resubmit original request & resolve response for original request
            if(response.status === 401 && response.data.error.code === 'GEN-TOKEN-EXPIRED') {
                this.refreshToken(this, request)
                .then(function(response){
                    resolve(response)
                })
            }
            // Otherwise just resolve the current response
            resolve(response)
        })
    },
    refreshToken: (context, request) => {
        return new Promise((resolve, reject) => {
            //Refresh token
            Api().post('/oauth/access_token')
            .then(function (response) {
                //Store refreshed token
                localStorage.setItem('id_token', response.data.token);

                Api().request(request)
                .then(function (newResponse) {
                    resolve(newResponse);
                });
            }, function (newResponse) {
                reject(newResponse);
            });
        });
    }
}
