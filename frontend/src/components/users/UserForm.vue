<template id="add-user-template" xmlns="http://www.w3.org/1999/xhtml">
  <div>
     <div id="inputRow" class="row">
          <div class="col-sm-3">
            <div class="input-group">
            User name:
              <input type="text" class="form-control" placeholder="Enter a username..." v-model="user.username" required>
            </div>
          </div>

          <div class="col-sm-2">
            <div class="input-group">
            User Password:
              <input type="text" class="form-control" placeholder="Enter password" v-model="user.password">
            </div>
          </div>
       <div class="col-sm-2">
         <div class="input-group">
           User firstname:
           <input type="text" class="form-control" placeholder="Enter firstname" v-model="user.firstname">
         </div>
       </div>
       <div class="col-sm-2">
         <div class="input-group">
           User lastname:
           <input type="text" class="form-control" placeholder="Enter surname" v-model="user.lastname">
         </div>
       </div>

        <!-- if the user is logged in this is value is preset by their ID after the page has been loaded -->
        <input type="hidden"  v-model="user.updateUser.id">

   <div class="col-sm-2">
      <div class="input-group">
      &nbsp
      </div>
        <div class="btn-group" role="group" aria-label="Add new vehicle">
          <button type="button" class="btn btn-success" @click="submit()">Add user</button>
        </div>
      </div>
  </div>
  </div>
</template>

<script>
import HotelService from '@/services/HotelService'
export default {
 //  props: ['countries', 'reload','fetchCountries','sortSearch'],
  data: function () {
    return {
      user:{username:'',password:'', firstname:'', surname:'', updateUser:{id:''}},
    }
  },
  created: function () {
    //this.authRecord=JSON.parse(localStorage.getItem('vuex')).auth.isAuthenticated;
    //this.user.updateUser.id=this.authRecord.id;
  },
   methods: {
    submit () {
      //This will call parent page with this action and pass this newly created object to it
      //this.$emit('add-user',user)

       return HotelService.createRootNoCatch('/user/',this.user)
              .then((res) => {
              if (res) {
                if (res.data) {
                    console.log('refresh entire list from userForm')
                     this.$emit('refresh-list')
                }
              }
            }).catch((error) => {
              if (error.response) {
                     this.$emit('user-errors', error.response.data.error);

              } else if ( error.request) {
                console.log("dddd"+error.request);
              } else {
                console.log('Error', error.message);
              }
            });
    }
  }
 }
</script>
