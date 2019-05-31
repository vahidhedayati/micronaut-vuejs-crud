<template id="search-contract-template" xmlns="http://www.w3.org/1999/xhtml">
  <div>
    <tabs :options="{ defaultTabHash: 'search' }">
      <tab name="search">
    <div id="inputRow" class="row">
      <div class="col-sm-2">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="name" v-model.lazy="search.name">
        </div>
      </div>
      <div class="col-sm-1">
        <div class="btn-group" role="group" aria-label="Search Hotels">
          <button type="button" class="btn btn-primary" @click="submit()">Search</button>
        </div>
      </div>
    </div>
      </tab>
      <tab name="add">
        <hotel-form
          @refresh-list="refreshHotels"
          @hotel-errors="errorHotels"
        >

        </hotel-form>
      </tab>
    </tabs>
  </div>
</template>
<script>
  import {Tabs, Tab} from 'vue-tabs-component';
  import HotelForm from './HotelForm'
  export default {
    components: {
      Tabs,
      HotelForm,
    },
    props: ['search'],
    model: {
      prop: 'search',
      event: 'change',
    },
    methods: {
      submit () {
        this.$emit('submit')
      },
      refreshHotels: function () {
          console.log('refresh entire list from searchForm')
          this.$emit('refresh-list');
      },
      errorHotels: function (errors) {
       console.log('hotelTable.vue updating hotel list')
          this.$emit('hotel-errors',errors);
      },
    }
  }
</script>
<style>
  .tabs-component {
    margin: 4em 0;
  }

  .tabs-component-tabs {
    border: solid 1px #ddd;
    border-radius: 6px;
    margin-bottom: 5px;
  }

  @media (min-width: 700px) {
    .tabs-component-tabs {
      border: 0;
      align-items: stretch;
      display: flex;
      justify-content: flex-start;
      margin-bottom: -1px;
    }
  }

  .tabs-component-tab {
    color: #999;
    font-size: 14px;
    font-weight: 600;
    margin-right: 0;
    list-style: none;
  }

  .tabs-component-tab:not(:last-child) {
    border-bottom: dotted 1px #ddd;
  }

  .tabs-component-tab:hover {
    color: #666;
  }

  .tabs-component-tab.is-active {
    color: #000;
     text-decoration: none;
    background:orange;
  }

  .tabs-component-tab.is-disabled * {
    color: #cdcdcd;
    cursor: not-allowed !important;
  }

  @media (min-width: 700px) {
    .tabs-component-tab {
      background-color: #fff;
      border: solid 1px #ddd;
      border-radius: 3px 3px 0 0;
      margin-right: .5em;
      transform: translateY(2px);
      transition: transform .3s ease;
    }

    .tabs-component-tab.is-active {
      border-bottom: solid 1px #fff;
      z-index: 2;
      transform: translateY(0);
    }
  }

  .tabs-component-tab-a {
    align-items: center;
    color: inherit;
    display: flex;
    padding: .75em 1em;
    text-decoration: none;
  }

  .tabs-component-panels {
    padding: 4em 0;
  }

  @media (min-width: 700px) {
    .tabs-component-panels {
      border-top-left-radius: 0;
      background-color: #fff;
      border: solid 1px #ddd;
      border-radius: 0 6px 6px 6px;
      box-shadow: 0 0 10px rgba(0, 0, 0, .05);
      padding: 4em 2em;
    }
  }
</style>
