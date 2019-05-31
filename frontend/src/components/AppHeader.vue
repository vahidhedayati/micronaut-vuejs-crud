<template id="app-header-template">
  <div class=" reduceHeading">
      <img :src = "require(`@/assets/${headerImage}.png`)" width="180px" height="100px"/>
      <h1>{{headerText}}</h1>
  </div>
</template>

<script>
  import Home from '@/components/Home'
  export default {
    name: 'app-header',
    props: {
      headerImage: {
        default: 'micronaut'

      },
      headerText: {
        default: 'Welcome to fab hotel'

      },
    },
    components: {
      Home
    }
  }
</script>

<style>
  .reduceHeading {
    zoom:70.50%;
    float:right;
    position:static;
    margin-top:-75px;
  }
</style>
