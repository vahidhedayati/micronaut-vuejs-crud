<template>
  <div id="app">
    <localizer>
      <navbar :loggedIn="loggedIn"></navbar>
      <div id="main-container" role="main">
        <section class="colset-2-its">

          <router-view/>

        </section>
      </div>
      <div class="footer row" role="contentinfo">
        <img src="./assets/hotel.png" alt="Logo Footer"/>
        <p><a href="http://locaLhost:3000">Fab Hotel Finder &copy; 2019</a></p>
      </div>
    </localizer>
  </div>

</template>

<script>
    import Localizer from './components/Localizer'
    import Navbar from './components/Navbar'
    export default {
        data: function () {
            return {
                loggedIn:false,
            }
        },
        created () {
            if (JSON.parse(localStorage.getItem('vuex')).user) {
                this.loggedIn = ((JSON.parse(localStorage.getItem('vuex')).user.token) ? true : false)
            }
        },
        watch: {
            $route(to, from) {
                //console.log('after', this.$route.path);
                if (JSON.parse(localStorage.getItem('vuex')).user) {
                    this.loggedIn=((JSON.parse(localStorage.getItem('vuex')).user.token)?true:false)
                }

            }
        },
        beforeRouteUpdate(to, from, next) {
            //this.loggedIn=((JSON.parse(localStorage.getItem('vuex')).user.token)?true:false)
            //console.log('before', this.$route.path);
            next();
        },
        computed: {
            currentLocale() {
                return this.$route.params.locale
            },
        },

        components: {
            Localizer,
            Navbar,

        },
    }
</script>

<style>

</style>
