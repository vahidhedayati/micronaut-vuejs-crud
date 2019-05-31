<template>
  <div>

    <nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
      <a class="navbar-brand" href="/#"><img src="../assets/images/grails.svg" alt="Grails Logo"/></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
              aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">

          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">Application Status <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li v-if="serverInfo" class="dropdown-item"><a href="#">Environment: {{serverInfo.environment}}</a></li>
              <li v-if="serverInfo" class="dropdown-item"><a href="#">App profile: {{serverInfo.appprofile}}</a></li>

              <li role="separator" class="divider dropdown-item"></li>
              <li v-if="serverInfo" class="dropdown-item"><a href="#">Grails version: {{serverInfo.grailsversion}}</a>
              </li>
              <li v-if="serverInfo" class="dropdown-item"><a href="#">Groovy version: {{serverInfo.groovyversion}}</a>
              </li>
              <li v-if="serverInfo" class="dropdown-item"><a href="#">JVM version: {{serverInfo.jvmversion}}</a></li>
              <li role="separator" class="divider dropdown-item"></li>
              <li v-if="serverInfo" class="dropdown-item"><a href="#">Reloading active:
                {{serverInfo.reloadingagentenabled ? 'true' :
                'false' }}</a></li>
            </ul>
          </li>

          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">Artefacts<span class="caret"></span></a>
            <ul class="dropdown-menu" v-if="serverInfo">
              <li v-if="serverInfo.artefacts"><a href="#">Controllers: {{serverInfo.artefacts.controllers}}</a></li>
              <li v-if="serverInfo.artefacts"><a href="#">Domains: {{serverInfo.artefacts.domains}}</a></li>
              <li v-if="serverInfo.artefacts"><a href="#">Services: {{serverInfo.artefacts.services}}</a></li>
            </ul>

          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">Installed Plugins <span class="caret"></span></a>
            <ul class="dropdown-menu" v-if="serverInfo">
              <li v-for="plugin in serverInfo.plugins" :key="plugin.name">
                <a href="#">{{ plugin.name }} - {{ plugin.version }}</a>
              </li>

            </ul>
          </li>

        </ul>
      </div>

    </nav>

    <div class="svg" role="presentation">
      <div class="grails-logo-container">
        <img src="../assets/images/grails-cupsonly-logo-white.svg" class="grails-logo"/>
        <span class="plus-logo">+</span>
        <img src="../assets/logo.png" class="hero-log"/>
      </div>
    </div>

    <div id="content" role="main">
      <section class="row colset-2-its">
        <h1>Welcome to Grails</h1>

        <p>
          Congratulations, you have successfully started your Grails & Vue.js application!
          While in development mode, changes will be loaded automatically when you edit your Vue.js app, without even
          refreshing the page.
          Below is a list of controllers that are currently deployed in
          this application, click on each to execute its default action:
        </p>

        <div id="controllers" role="navigation">
          <h2>Available Controllers:</h2>
          <ul v-if="serverInfo">

            <li v-if="serverInfo.controllers" v-for="controller in serverInfo.controllers" :key="controller.name">
              <a :href="serverURL + '/' + controller.logicalPropertyName">{{controller.name }}</a></li>
          </ul>
        </div>
      </section>
    </div>
    <div class="footer row" role="contentinfo">
      <div class="col-md-4">
        <a href="http://guides.grails.org" target="_blank">
          <img src="../assets/images/advancedgrails.svg" alt="Grails Guides" class="float-left"/>

        </a>
        <strong class="centered"><a href="http://guides.grails.org" target="_blank">Grails Guides</a></strong>
        <p>Building your first Grails app? Looking to add security, or create a Single-Page-App? Check out the <a
          href="http://guides.grails.org" target="_blank">Grails Guides</a> for step-by-step tutorials.</p>

      </div>
      <div class="col-md-4">
        <a href="http://docs.grails.org" target="_blank">
          <img src="../assets/images/documentation.svg" alt="Grails Documentation" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://docs.grails.org" target="_blank">Documentation</a></strong>
        <p>Ready to dig in? You can find in-depth documentation for all the features of Grails in the <a
          href="http://docs.grails.org" target="_blank">User Guide</a>.</p>

      </div>

      <div class="col-md-4">
        <a href="https://grails-slack.cfapps.io" target="_blank">
          <img src="../assets/images/slack.svg" alt="Grails Slack" class="float-left"/>
        </a>
        <strong class="centered"><a href="https://grails-slack.cfapps.io" target="_blank">Join the
          Community</a></strong>
        <p>Get feedback and share your experience with other Grails developers in the community <a
          href="https://grails-slack.cfapps.io" target="_blank">Slack channel</a>.</p>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'Welcome',
  data () {
    return {
      msg: 'Welcome to Your Grails & Vue.js App',
      serverInfo: null,
      showLinks: false,
      serverURL: process.env.SERVER_URL
    }
  },
  methods: {
    toggleLinks: function () {
      this.showLinks = !this.$data.showLinks
    }
  },
  created: function () {
    fetch(`${this.$data.serverURL}/application`)
      .then(response => response.json())
      .then(json => {
        this.serverInfo = json
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .navbar-nav li button {
    background: transparent;
    border: none;
    line-height: 20px;
    font-size: 18px;
    color: white;
    padding: 25px 15px;
  }

  .btn-primary:active, .btn-primary.active, .open > .dropdown-toggle.btn-primary {
    background-color: #e7e7e7;
  }

  button span.caret {
    margin-left: 2px;
  }

  .plus-logo {
    font-size: 10rem;
    margin-left: -100px;
    margin-right: -13px;
  }

  .hero-logo {
    width: 161px;
    margin-right: -161px;
    margin-bottom: 88px;
  }
</style>
