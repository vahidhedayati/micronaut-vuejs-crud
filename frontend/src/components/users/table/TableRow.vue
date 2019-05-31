
<template id="tablerow-template" xmlns="http://www.w3.org/1999/xhtml">
    <tr>
      <td>{{ user.id }}</td>
      <td>
          <span v-if="showForm">
              <input  v-model="updatedUser.username">
          </span>
          <span v-else>
              {{ user.username }}
          </span v-else>
      </td>
      <td>
          <span v-if="showForm">
                <input  v-model="updatedUser.firstname">
          </span>
          <span v-else>
              {{ user.firstname }}
            </span v-else>
       </td>
      <td>
          <span v-if="showForm">
                <input  v-model="updatedUser.surname">
          </span>
        <span v-else>
              {{ user.surname }}
            </span v-else>
      </td>

      <td>  {{ user.lastUpdated | shortMoment() }}
      </td>
      <td class="dropdown dropdown-table">
        <span v-if="showForm">
            <button v-on:click="save(updatedUser)">Save</button>
        </span>
        <span v-else>
          <button v-on:click="editUser(user)">Edit</button>
          <span class="hoverButton">
      <vue-dropdown :config="config"
                    @setSelectedOption="actionDropDown($event,user.id)"
      ></vue-dropdown>
            </span>
        </span>
      </td>
    </tr>
</template>

<script>
//This is needed for the select component to work
import FieldSelect from '../../form/FieldSelect'
import HotelService from '@/services/HotelService'
import moment from 'moment';
import VueMoment from 'vue-moment'
import VueDropdown from 'vue-dynamic-dropdown'
export default {
    //You must declare what is being passed in otherwise they wont work..
   props: ['user', 'makes', 'models', 'drivers','reload','updatedResults'],
   data () {
        return {
          response: [],
          errors: [],
          newUser:{username:'',firstname:'',surname:'',updateUser:{id:''}},
          showUser:null,
          updatedUser:{},
          serverURL: process.env.SERVER_URL,
          showForm: false,
          config: {
            options: [
              {
                value: "display"
              },
              {
                value: "delete"
              },
            ],
            prefix: "",
            backgroundColor: "transparent"
          }

        }
      },
  computed: {
    
  },
      //This is needed for the select component to work
      components: {
          FieldSelect,
        VueMoment,
        moment,
        VueDropdown
        },
      filters: {
        moment: function (date) {
          return moment(date).format('MMMM Do YYYY, h:mm:ss a');
        },
        shortMoment: function (date) {
          return moment(date).format('DD MMM YYYY');
        }

      },
       methods: {
         moment: function () {
           return moment();
         },
         editUser() {
          this.showForm=true;
          this.updatedUser=this.user;
         },
         /**
          * This controls the drop down functionality
          * @param event
          * @param id
          * @returns {boolean}
          */
         actionDropDown(event,id) {
           console.log(' jj '+JSON.stringify(event)+' --- '+id)

           if (event.value==='display') {
                console.log('display '+id)
             return HotelService.fetchRoot('/user/'+id)
               .then((res) => {
               if (res) {
                 console.log('RES: '+JSON.stringify(res));
               }
               if (res.data) {
               //this.showForm=false;
               console.log('show resData '+JSON.stringify(res.data))
               this.$emit('user-show', res.data);
               //showUser=res.data;
               //showDialog=true
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


           if (event.value==='delete') {
             if (confirm('Delete record?')) {
               console.log('delete '+id)

               return HotelService.deleteNoCatch('/user/', id)
                 .then((res) => {
                 if (res) {
                   console.log('RES: '+JSON.stringify(res));
                 }

                 if (res.data||res.status===200) {
                  // we need to reload page
                  this.$emit('refresh-list');
                 }
             }).catch((error) => {
                 if (error.response) {
                   console.log('errors '+ JSON.stringify(error.response))
                 //this.$emit('user-errors', error.response.data.error);

               } else if ( error.request) {
                 console.log("dddd"+error.request);
               } else {
                 console.log('Error', error.message);
               }
             });


             } else {
               console.log('denied')
             }

           }


         },
         updateValue: function (value) {
           this.$emit('input', value);
         },
         save() {
           const newName = this.updatedUser;
           console.log('new Name =  '+JSON.stringify(newName))
           //+newName.id
           this.newUser.id=newName.id
           this.newUser.code=newName.code
           this.newUser.name=newName.name
           console.log('new newUser =  '+JSON.stringify( this.newUser))
           return HotelService.putRootNoCatch('/user/update/'+newName.id, this.newUser)
             .then((res) => {
             if (res) {
                console.log('RES: '+JSON.stringify(res));
             }
             if (res.data||res.status===204) {
               this.showForm=false;
               console.log('resData '+JSON.stringify(res.data))
               this.$emit('user-update', res.data);
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

<!-- Per Component Custom CSS Rules -->
<style>
.dropdown-label.text {
    font-size:0.8em;
  }
.dropdown-label-container {
    margin-top: -30px !important;
  }
.dropdown-label-container .angle-down {
    margin-top: -10px !important;
    margin-left: -10px !important;
    background-color: orange !important;
  }
.hoverButton {
    margin-left:-70px;
    display: inline-block !important;
    width:30px !important;
    font-size:0.8em;

  }
.dropdown-table .options {
    width:80px !important;
    font-size:0.8em;
    background: red;
  }
  /* Add custom rules here */
</style>
