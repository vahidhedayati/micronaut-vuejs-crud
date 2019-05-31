<template>
  <div id="custom1">

    <!-- The refresh and user errors are being returned by CountryForm which is the add tab of search form -->
    <search-form v-model="search"
                 @submit="searchUsers()"
                 @refresh-list="refreshUsers"
                 @user-errors="errorUsers"
    ></search-form>

    <!-- any errors caused by any underlying processes on this page -->
    <ul v-show="errors.length>0"  class="errors"><li v-for="error in errors">{{ error }}</li></ul>


    <!-- this loads up the entire user listing -->
    <user-table :users="users"
                   v-bind="{fetchUsers,sortSearch}"
                   @user-update="updateUsers"
                   @refresh-list="refreshUsers"
                   @user-errors="errorUsers"
    >
    </user-table>


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
  import UserTable from './table/UserTable'
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

      UserTable,
      Pagination,
      moment
    },
    data: function () {
      return {
        errors:[],
        users: [],
        search:{name:''},
        user: {},
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
            this.fetchUsers(0)
          ])
        } catch (error) {
          console.log(error)
        }
      },
      initialiseUsers(params){
        console.log(' getting user /user/list'+params)
        return HotelService.fetchRoot('/user/list?'+params)
          .then((res) => {
          if (res) {
            console.log('full results '+JSON.stringify(res));
            if (res.data.instanceList) {
              console.log("rr "+res.data.instanceList)
              this.users = res.data.instanceList;
              this.total=res.data.instanceTotal;
              this.numberOfPages=res.data.numberOfPages;
            } else {
              if (res.data) {
                //console.log("rr "+res.data.objects)
                this.users = res.data;
              }
            }
          }
        });
      },
      pagechanged: function(page) {
        console.log("Page = "+page)
        this.currentPage = page;
        this.offset=(page*this.max)-this.max
        this.fetchUsers(this.offset)
      },
      sortSearch(currentSort,currentSortDir) {
        var variables = $.param(this.search);
        this.currentSort=currentSort;
        this.currentSortDir=currentSortDir;
        variables+="&sort="+currentSort+"&order="+currentSortDir+'&offset='+ this.offset;
        this.initialiseUsers(variables);
      },
      refreshUsers: function () {
        console.log('refreshing entire list after a new entry was added')
        this.fetchUsers(0)
      },
      updateUsers: function (user) {
        this.errors=[];
        console.log('user.vue updating user list')
        this.users=updateObjectsInArr(this.users, [user])
      },
      errorUsers: function (errors) {
        console.log('userTable.vue updating error list')
        this.errors=errors;
      },
      fetchUsers: function (pageNumber) {
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
        console.log("Fetching users "+pageNumber)
        this.initialiseUsers(variables);
      },
      searchUsers: function () {
        var variables = $.param(this.search);
        console.log('variables: '+variables)
        this.initialiseUsers(variables);
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
