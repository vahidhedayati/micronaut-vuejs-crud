<template id="add-hotel-template" xmlns="http://www.w3.org/1999/xhtml">
  <div id="validated-form">

     <div id="inputRow" class="row">
          <div class="col-sm-3">
            <div class="input-group">
            Hotel name:

              <input type="text" class="form-control"  pattern="(?=.*[A-Za-z0-9]).{3,55}" placeholder="Enter a name..." title="Hotel name: 3 - 55 characters only " v-model="hotel.name" >
            </div>
          </div>

          <div class="col-sm-2">
            <div class="input-group">
            Hotel Code:

              <input type="text" class="form-control"  pattern="(?=.*[A-Z]).{2,4}" placeholder="Enter a code..." title="Hotel code: Upper Case A-Z 2 to 4 characters only" v-model="hotel.code" required>

            </div>
          </div>

       <div>
         <label>Phone:</label>
         <input type="text" v-model="hotel.phone" value />
       </div>
       <div>
         <label>Email:</label>
         <input type="text" v-model="hotel.email" />
       </div>

<!-- please note hidden fields not needed but to represent what happens with autoComplete below -->
         <input type="hidden" v-model="hotel.updateUser.username" />
         <input type="hidden" v-model="hotel.updateUser.id" />


         <autocomplete form-field="search"
                       @key-press="updateAutoCompleteItems"
                       @search-value="updateSearchValue"
                       @search-key="updateSearchKey"
                       key-field="id" value-field="username"
                       :items="users" />

        <!-- if the user is logged in this is value is preset by their ID after the page has been loaded -->
        <input type="hidden"  v-model="hotel.updateUser.id">

   <div class="col-sm-2">
      <div class="input-group">
      &nbsp
      </div>
        <div class="btn-group" role="group" aria-label="Add new vehicle">
          <button type="button" class="btn btn-success" @click.prevent="submitForm()">Add hotel</button>
        </div>
      </div>
  </div>
  </div>
</template>

<script>
import $ from 'jquery';
import HotelService from '@/services/HotelService'
import Autocomplete from '../Autocomplete'
const validateEmail= email => {
  if (!email.length) {
    return { valid: false, error: "email_needed"};
  }
  if (!email.match(/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/)) {
    return { valid: false, error: "email_invalid" };
  }
  return { valid: true, error: null };
};
const validateName = name => {
  if (!name.length) {
    return { valid: false, error: "Name field is required" };
  }
  return { valid: true, error: null };
};
const validatePhone = phone => {
  if (!phone.length) {
    return { valid: false, error: 'phone_required' };
  }

  if (!phone.match(/^[+][(]?[0-9]{1,3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,7}$/gm)) {
    return { valid: false, error: 'phone_invalid' };
  }

  return { valid: true, error: null };
}
export default {
 //  props: ['countries', 'reload','fetchCountries','sortSearch'],
  data: function () {
    return {
      valid: true,
        users: [],
      errors: [],
      hotel:{name:'AAAAAAAAAAAAA',code:'AAAA',phone:'+44-123456789', email:'aa@aa.com', updateUser:{id:'', username:''}}
    }
  },
  components: {
    'validateEmail': validateEmail,
    'validatePhone': validatePhone,
    'validateName' : validateName,
     'autocomplete' : Autocomplete

  },
  created: function () {
    //this.authRecord=JSON.parse(localStorage.getItem('vuex')).auth.isAuthenticated;
    //this.hotel.updateUser.id=this.authRecord.id;
  },
   methods: {
   updateAutoCompleteItems: function (searchValue) {
      if (searchValue.length>2) {
        this.users=[];
        var variables = $.param(searchValue);
        variables+="&max=10&offset=0";
        this.initialiseUsers(variables);
      }
    },
    initialiseUsers(params){
      return HotelService.fetchRoot('/user/list?'+params)
        .then((res) => {
        if (res) {
          if (res.data.instanceList) {
           this.users = res.data.instanceList;
          } else {
            if (res.data) {
              this.users = res.data;
            }
          }
        }
      });
    },
     updateSearchValue: function (value) {
       this.hotel.updateUser.username=value
     },
     updateSearchKey: function (key) {
       this.hotel.updateUser.id=key
     },
     submitForm () {
       this.errors=[];
       const validName = validateName(this.hotel.name);
       this.errors.push(validName.error);
       this.valid = validName.valid


       const validPhone = validatePhone(this.hotel.phone);
       this.errors.push(validPhone.error);
       this.valid = validPhone.valid

       const validEmail = validateEmail(this.hotel.email);
       this.errors.push(validEmail.error);
       this.valid = validEmail.valid



       /**
        * IF form is valid for submission submit it - otherwise fail front end validation:
        */
       if (this.valid) {
         console.log('submitting valid form')
        this.submit();
       } else if (this.errors.length>0) {
         console.log('ERRors on page" '+this.errors)
         this.$emit('hotel-errors',this.errors);
       }
     },
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

           this.$emit('hotel-errors',   error.response.data);

           if (error.response) {
              //this.$emit('hotel-errors', err.code);

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
