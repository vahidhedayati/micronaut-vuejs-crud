<template>
  <div id="app">
    <localizer>
    <navbar></navbar>
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
