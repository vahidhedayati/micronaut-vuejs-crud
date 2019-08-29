<template>

  <nav  id="nav" class="site-header navbar navbar-expand-lg navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#"><img src="../assets/github.svg" alt="Grails Logo"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
            aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
      <ul class="nav">
        {{$t('app_name')}}
        <localized-link
          tag="li"
          to="home"
          class="nav-item"
          active-class="active"
        >
          <a class="nav-link">
            {{$t('home.label')}}
          </a>
        </localized-link>
        <localized-link
          tag="li"
          to="hotel"
          class="nav-item"
          active-class="active"
        >
          <a class="nav-link">
            {{$t('hotel_label')}}
          </a>
        </localized-link>

        <localized-link
          tag="li"
          to="users"
          class="nav-item"
          active-class="active"
        >
          <a class="nav-link">
            {{$t('users_label')}}
          </a>
        </localized-link>

        <router-link  role="button" v-show="loggedIn" :to="{name: 'logout'}">
          <a  class="logout-options" role="button" > Sign out</a>
        </router-link>
        <localized-link  tag="li"   v-show="!loggedIn" to="login" class="dropdown" active-class="active">
          <a  class="logout-options" role="button" > Sign in</a>
        </localized-link>
        <locale-switcher />

      </ul>
      <ul class="nav navbar-nav ml-auto">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="true">Status <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li>Option 1</li>
          </ul>
        </li>

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="true">Dropdown 1<span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li>Option 1</li>
          </ul>

        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
             aria-expanded="true">Dropdown 2 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li>Option 1</li>

          </ul>
        </li>

      </ul>
    </div>

  </nav>
</template>

<script>
  import LocaleSwitcher from './LocaleSwitcher'
  import LocalizedLink from './LocalizedLink'
  export default {
      props: ['loggedIn'],
    components: {
      LocaleSwitcher,
      LocalizedLink
    },
  }
</script>


<style scoped>
  /* ... */
</style>
