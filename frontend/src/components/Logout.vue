<template>
  <div>Logout</div>
</template>
<script>
  import UsersService from '@/services/UsersService'
  export default {
    data (){
      return {
      }
    },
    created:function(){
      //UsersService.logout();
      //http://alvarosanchez.github.io/grails-spring-security-rest/latest/docs/#_logout
      //Logout via jwt can simply be done by resetting the client data -
      this.$store.dispatch('user/userLogged',new Boolean(false));
      localStorage.setItem('id_token', null);
      this.$store.dispatch('auth/login',  null);
      this.$store.dispatch('user/setToken', null);


        this.$router.push({name:'home'})
    }
  }
</script>
