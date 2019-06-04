<template>
  <div id="custom1">

    <!-- The refresh and hotel errors are being returned by CountryForm which is the add tab of search form -->
    <search-form v-model="search"
                 @submit="searchHotels()"
                 @refresh-list="refreshHotels"
                 @hotel-errors="errorHotels"
    ></search-form>

    <!-- any errors caused by any underlying processes on this page -->
    <ul v-show="errors.length>0"  class="errors"><li v-for="error in errors">
     actual_message: error -
             -- translated_code:  {{$t(error)}}
    </li></ul>


    <!-- this loads up the entire hotel listing -->
    <hotel-table :hotels="hotels"
                   v-bind="{fetchHotels,sortSearch}"
                   @hotel-update="updateHotels"
                   @refresh-list="refreshHotels"
                   @hotel-errors="errorHotels"
    >
    </hotel-table>


    <!-- generic pagination -->
    <Pagination
      :maxVisibleButtons=3
      :totalPages="numberOfPages"
      :total="total"
      @sortSearch="sortSearch"
      :currentPage="currentPage"
      @pagechanged="pagechanged"/>
  </div>
</template>

<script>
  import $ from 'jquery';

  import SearchForm from './SearchForm'
  import HotelService from '@/services/HotelService'
  import HotelTable from './table/HotelTable'
  import Pagination from '../Pagination'
  import moment from 'moment';


  const newObjInInitialArr = function(initialArr, newObject) {
    let id = newObject.id;
    let newArr = [];
    for (let i = 0; i < initialArr.length; i++) {
      if (id === initialArr[i].id) {
        newArr.push(newObject);
      } else {
        newArr.push(initialArr[i]);
      }
    }
    return newArr;
  };

  const updateObjectsInArr = function(initialArr, newArr) {
    let finalUpdatedArr = initialArr;
    for (let i = 0; i < newArr.length; i++) {
      finalUpdatedArr = newObjInInitialArr(finalUpdatedArr, newArr[i]);
    }
    return finalUpdatedArr
  }

  export default {
    components: {
      SearchForm,

      HotelTable,
      Pagination,
      moment
    },
    data: function () {
      return {
        errors:[],
        hotels: [],
        search:{name:''},
        hotel: {},
        serverURL: process.env.SERVER_URL,
        total: 0,
        max: 10,
        offset:0,
        currentPage:1,
        numberOfPages:0,
        currentSort:'',
        currentSortDir:'asc'
      }
    },
    created () {
      this.fetchData()
    },
    methods: {
      fetchData: async function () {
        try {
          Promise.all([
            this.fetchHotels(0)
          ])
        } catch (error) {
          console.log(error)
        }
      },
      initialiseHotels(params){
        console.log(' getting /list'+params)
        return HotelService.fetchRoot('/list?'+params)
          .then((res) => {
          if (res) {
            console.log('full results '+JSON.stringify(res));
            if (res.data.instanceList) {
              console.log("rr "+res.data.instanceList)
              this.hotels = res.data.instanceList;
              this.total=res.data.instanceTotal;
              this.numberOfPages=res.data.numberOfPages;
            } else {
              if (res.data) {
                //console.log("rr "+res.data.objects)
                this.hotels = res.data;
              }
            }
          }
        });
      },
      pagechanged: function(page) {
        console.log("Page = "+page)
        this.currentPage = page;
        this.offset=(page*this.max)-this.max
        this.fetchHotels(this.offset)
      },
      sortSearch(currentSort,currentSortDir) {
        var variables = $.param(this.search);
        this.currentSort=currentSort;
        this.currentSortDir=currentSortDir;
        variables+="&sort="+currentSort+"&order="+currentSortDir+'&offset='+ this.offset;
        this.initialiseHotels(variables);
      },
      refreshHotels: function () {
        console.log('refreshing entire list after a new entry was added')
        this.fetchHotels(0)
      },
      updateHotels: function (hotel) {
        this.errors=[];
        console.log('hotel.vue updating hotel list')
        this.hotels=updateObjectsInArr(this.hotels, [hotel])
      },
      errorHotels: function (errors) {
        console.log('hotelTable.vue updating error list')
        this.errors=errors;
      },
      fetchHotels: function (pageNumber) {
        var variables=''
        if (this.search) {
          variables += $.param(this.search);
        }
        if (this.currentSort) {
          variables+="&sort="+this.currentSort+"&order="+this.currentSortDir;
        }
        if (variables!='') {
          variables+='&offset='+pageNumber
        } else {
          variables='?offset='+pageNumber
        }
        console.log("Fetching hotels "+pageNumber)
        this.initialiseHotels(variables);
      },
      searchHotels: function () {
        var variables = $.param(this.search);
        console.log('variables: '+variables)
        this.initialiseHotels(variables);
      }
    }
  }
</script>
<style>
  #custom {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    text-align: center;
    color: #2c3e50;
  }
  .reduceZoom {
    zoom:25.50%;

  }
</style>
