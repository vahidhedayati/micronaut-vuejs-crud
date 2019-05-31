<template>
  <div>
  <table class="table">
    <thead class="thead-inverse">
      <tr>
        <th @click="sort('id')" :class="{active: this.column === 'id'}">
          ID
          <span :class="{blank:this.column != 'id',
          arrow: (this.column === 'id'),
          asc: (this.column === 'id' && this.currentSortDir==='asc'),
           desc: this.column === 'id' && this.currentSortDir==='desc'}">
          </span>

        </th>
        <th @click="sort('username')" :class="{active: this.column === 'username'}">
          username
          <span :class="{blank:(this.column != 'username'),
          arrow: (this.column === 'username'),
          asc: (this.column === 'username' && this.currentSortDir==='asc'),
           desc: (this.column ==='username' && this.currentSortDir==='desc')}">
          </span>
        </th>
        <th @click="sort('firstname')" :class="{active: this.column === 'firstname'}">
          firstname
          <span :class="{blank:(this.column != 'firstname'),
          arrow: (this.column === 'firstname'),
          asc: (this.column === 'firstname' && this.currentSortDir==='asc'),
           desc: (this.column ==='firstname' && this.currentSortDir==='desc')}">
          </span>
        </th>
        <th @click="sort('lastname')" :class="{active: this.column === 'lastname'}">
          lastname
          <span :class="{blank:(this.column != 'lastname'),
          arrow: (this.column === 'lastname'),
          asc: (this.column === 'lastname' && this.currentSortDir==='asc'),
           desc: (this.column ==='lastname' && this.currentSortDir==='desc')}">
          </span>
        </th>
        <th @click="sort('lastUpdated')"  :class="{active: this.column === 'lastUpdated'}">
          last Updated
          <span :class="{blank:(this.column != 'lastUpdated'),
          arrow: (this.column === 'lastUpdated'),
          asc: (this.column === 'lastUpdated' && this.currentSortDir==='asc'),
           desc: (this.column ==='lastUpdated' && this.currentSortDir==='desc')}">
          </span>
        </th>
        <th>Action</th>

      </tr>
    </thead> <!--1-->
      <!-- sortedCats replaces vehicles and is only sorting by current paginated dataset -->
      <table-row v-for="user in users"
                  :key="user.id"
                  :user="user"
                  @user-update="updateUsers"
                 @refresh-list="refreshUsers"
                 @user-show="showUser"
                  @user-errors="errorUsers"
                 v-bind="{fetchUsers}"
                ></table-row> <!--2-->
  </table>

    <display-user-modal
                 :show="showModal"
                 :user="currentShowUser"
                 @close="closeModalPopup"></display-user-modal>

  </div>
</template>

<script>
import TableRow from './TableRow.vue';
import DisplayUserModal from './DisplayUserModal.vue';
export default {
   props: ['users', 'reload','fetchUsers','sortSearch'],
  components: { // <3>
    TableRow,
    DisplayUserModal
  },
  data: function () {
  return {
    updatedResults:null,
    currentShowUser:null,
    currentSort:'contractName',
    currentSortDir:'asc',
    column:'id',
    activeColumn:'id',
    currentStyle:'',
    showModal:false
  }
},

  methods: {
    sort: function (s) {
      this.column=s;
      this.activeColumn=s;
      //if s == current sort, reverse
      if (s === this.currentSort) {
        this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';

      }
      this.currentSort = s;

      //This is full on search and sortedSearch sits in the parent page Custom.vue
      this.sortSearch(s,this.currentSortDir)

    },
     updateUsers: function (user) {
     console.log('userTable.vue updating user list')
        this.$emit('user-update',user);
      },
    showUser: function (user) {
      //This is when the tableRow returned current current to be displayed - this updates the internal user
      //of this page to be that.
      this.currentShowUser=user;
      this.showModal=true;
      //
    },
    closeModalPopup: function () {
      this.showModal = false;
      this.currentShowUser = null;
    },
    refreshUsers: function () {
      console.log('userTable.vue refresh user list')
      this.$emit('refresh-list');
    },
   errorUsers: function (errors) {
      console.log('userTable.vue updating user list')
      this.$emit('user-errors',errors);
    },
  },
  computed:{

  }
}

</script>

<style>
  th.active .arrow {
    opacity: 1;
  }

  .arrow {
    display: inline-block;
    vertical-align: middle;
    width: 0;
    height: 0;
    margin-left: 5px;
    opacity: 0.66;
  }

  .arrow.asc {
    border-left: 4px solid transparent;
    border-right: 4px solid transparent;
    border-bottom: 4px solid #42b983;
  }

  .arrow.desc {
    border-left: 4px solid transparent;
    border-right: 4px solid transparent;
    border-top: 4px solid #42b983;
  }
  th.active {
    color:orange !important;
  }


  blank {

  }
</style>
