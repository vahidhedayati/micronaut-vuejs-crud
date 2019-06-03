<template id="add-hotel-template" xmlns="http://www.w3.org/1999/xhtml">
  <div id="validated-form">
    <ul v-show="errors.length>0"  class="errors"><li v-for="error in errors">{{ error }}</li></ul>
     <div id="inputRow" class="row">
          <div class="col-sm-3">
            <div class="input-group">
            Hotel name:

              <input type="text" class="form-control" pattern="(?=.*[A-Za-z0-9]).{3,55}" placeholder="Enter a name..." title="Hotel name: 3 - 55 characters only " v-model="hotel.name" >
            </div>
          </div>

          <div class="col-sm-2">
            <div class="input-group">
            Hotel Code:

              <input type="text" class="form-control" pattern="(?=.*[A-Z]).{2,4}" placeholder="Enter a code..." title="Hotel code: Upper Case A-Z 2 to 4 characters only" v-model="hotel.code" required>

            </div>
          </div>

       <div>
         <label>Phone:</label>
         <input type="text" v-model="hotel.phone" />
       </div>
       <div>
         <label>Email:</label>
         <input type="text" v-model="hotel.email" />
       </div>


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
import HotelService from '@/services/HotelService'
const validateEmail= email => {
  if (!email.length) {
    return { valid: false, error: "Email field is required" };
  }
  if (!email.match(/^\w+([.-]?\w+)_@\w+(_[_.-]?\w+)_(.\w{2,3})+$/)) {
    return { valid: false, error: "Please, enter a valid email." };
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
    return { valid: false, error: 'Phone field is required.' };
  }

  if (!phone.match(/^[+][(]?[0-9]{1,3}[)]?[-\s.]?[0-9]{3}[-\s.]?[0-9]{4,7}$/gm)) {
    return { valid: false, error: 'Please, enter a valid international phone number.' };
  }

  return { valid: true, error: null };
}
export default {
 //  props: ['countries', 'reload','fetchCountries','sortSearch'],
  data: function () {
    return {
      valid: true,
      errors: [],
      hotel:{name:'',code:'',phone:'', email:'', updateUser:{id:''}}
    }
  },
  components: {
    'validateEmail': validateEmail,
    'validatePhone': validatePhone,
    'validateName' : validateName

  },
  created: function () {
    //this.authRecord=JSON.parse(localStorage.getItem('vuex')).auth.isAuthenticated;
    //this.hotel.updateUser.id=this.authRecord.id;
  },
   methods: {
     submitForm () {

       const validName = validateName(this.hotel.name);
       this.errors.push(validName.error);
       if (this.valid) {
         this.valid = validName.valid
       }

       const validPhone = validatePhone(this.hotel.phone);
       this.errors.push(validPhone.error);
       if (this.valid) {
         this.valid = validPhone.valid
       }

       const validEmail = validateEmail(this.hotel.email);
       this.errors.push(validEmail.error);
       if (this.valid) {
         this.valid = validEmail.valid
       }


       /**
        * IF form is valid for submission submit it - otherwise fail front end validation:
        */
       if (this.valid) {
        this.submit();
       } else {
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
              console.log(' aahh '+JSON.stringify(error))
              if (error.response) {
              this.$emit('hotel-errors', error.response.data);

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
