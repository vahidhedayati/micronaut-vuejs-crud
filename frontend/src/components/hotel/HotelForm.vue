<template id="add-hotel-template" xmlns="http://www.w3.org/1999/xhtml">
  <div>
     <div id="inputRow" class="row">
          <div class="col-sm-3">
            <div class="input-group">
            Hotel name:
              <input type="text" class="form-control" placeholder="Enter a name..." v-model="hotel.name" required>
            </div>
          </div>

          <div class="col-sm-2">
            <div class="input-group">
            Hotel Code:
              <input type="text" class="form-control" placeholder="Enter code" v-model="hotel.code">
            </div>
          </div>


        <!-- if the user is logged in this is value is preset by their ID after the page has been loaded -->
        <input type="hidden"  v-model="hotel.updateUser.id">

   <div class="col-sm-2">
      <div class="input-group">
      &nbsp
      </div>
        <div class="btn-group" role="group" aria-label="Add new vehicle">
          <button type="button" class="btn btn-success" @click="submit()">Add hotel</button>
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
      hotel:{name:'',code:'',updateUser:{id:''}},
    }
  },
  created: function () {
    //this.authRecord=JSON.parse(localStorage.getItem('vuex')).auth.isAuthenticated;
    //this.hotel.updateUser.id=this.authRecord.id;
  },
   methods: {
    submit () {
      //This will call parent page with this action and pass this newly created object to it
      //this.$emit('add-hotel',hotel)

       return HotelService.createRootNoCatch('/',this.hotel)
              .then((res) => {
              if (res) {
                if (res.data) {
                    console.log('refresh entire list from hotelForm')
                     this.$emit('refresh-list')
                }
              }
            }).catch((error) => {
              if (error.response) {
                     this.$emit('hotel-errors', error.response.data.error);

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
