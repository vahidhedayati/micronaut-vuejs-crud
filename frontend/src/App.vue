<template>
  <div id="app">

    <nav  id="nav" class="site-header navbar navbar-expand-lg navbar-static-top" role="navigation">
      <a class="navbar-brand" href="/#"><img src="./assets/github.svg" alt="Grails Logo"/></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
              aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav">
          <li  class="dropdown"><router-link class="site-header" to="/">Home</router-link></li>
          <li  class="dropdown"><router-link to="/hotel">Hotels</router-link></li>
          <li  class="dropdown"><router-link to="/users">Users</router-link></li>
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


    <div id="main-container" role="main">
      <section class="colset-2-its">

          <router-view/>

      </section>
    </div>
    <div class="footer row" role="contentinfo">
      <img src="./assets/hotel.png" alt="Logo Footer"/>
      <p><a href="http://locaLhost:3000">Fab Hotel Finder &copy; 2019</a></p>
    </div>
  </div>
</template>


<style>

</style>
