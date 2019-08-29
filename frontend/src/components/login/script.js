import UsersService from '@/services/UsersService'
import ProfileService from '@/services/ProfileService'
export default {
  data () {
    return {
      context: 'login context',
      username:'',
      password:'',
      data: {
        rememberMe: false,
        fetchUser: true
      },
      error: null
    }
  },
  computed: {
    isAuthenticated: function () {
      return this.$store.getters.isAuthenticated
    }
  },
  methods: {
    processForm: function () {
      console.log({name: this.username, password: this.password});
      UsersService.login({
        username:  this.username,
        password:this.password
      })
        .then((response) => {
          console.log(' 0000 '+JSON.stringify(response))
        if (response.data) {
        console.log('Login >>>>>>>>', response.data)//.access_token);
        //localStorage.setItem('id_token',  response.access_token)
        this.$store.dispatch('auth/login',  response.data);
        this.$store.dispatch('user/setToken', response.data.access_token);
        this.$store.dispatch('user/userLogged',new Boolean(true));

        this.getProfile(response.data.id);

        if(response.data.roles[0] === 'ROLE_ADMIN') {
          this.$router.push({name:'home'})
        } else if(response.data.roles[0] === 'ROLE_DRIVER') {
          this.$router.push({name:'home'})
        } else {
          this.$router.push({name:'home'})
        }
      }
    })
    },
    getProfile (userId) {
      console.log(userId)
      ProfileService.fetchProfile(userId)
        .then((res) => {
        console.log("PROFILE" +res.data)
      this.$store.dispatch('user/setProfile', res.data)
      //this.$router.push({name: 'Home'})
    })
    },
    authenticate: function (provider) {
      this.$auth.authenticate(provider).then(function () {
        // Execute application logic after successful social authentication
      })
    }
  }
}

/*



        this.$auth.login({
             username: this.data.body.username,
             password: this.data.body.password
           })
           .then((response) => {
             console.log('Login >>>', response)
             this.$store.dispatch('auth/login',  response)
             this.$store.dispatch('user/setToken', response.data.token)
             this.$store.dispatch('user/userLogged', true)
             //this.getProfile(response.data.set._id);
             //console.log(response.data.set.role+"------------------");

      if(response.data.roles[0] === 'Admin') {
        this.$router.push({name: 'Admin'})
      } else if(response.data.roles[0] === 'ROLE_DRIVER') {
        this.$router.push({name: 'Driver'})
      } else {
        this.$router.push({name: 'User'})
      }
         })


  }
 */
