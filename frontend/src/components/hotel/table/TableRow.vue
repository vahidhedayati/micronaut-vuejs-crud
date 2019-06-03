
<template id="tablerow-template" xmlns="http://www.w3.org/1999/xhtml">
    <tr>
      <td>{{ hotel.id }}</td>
      <td>
        <span v-if="showForm">
            <input  v-model="updatedHotel.name">
        </span>
        <span v-else>
            {{ hotel.name }}
        </span v-else>
      </td>
      <td>
        <span v-if="showForm">
              <input  v-model="updatedHotel.code">
        </span>
        <span v-else>
            {{ hotel.code }}
          </span v-else>
     </td>
      <td>
        <span v-if="showForm">
              <input  v-model="updatedHotel.phone">
        </span>
        <span v-else>
            {{ hotel.phone }}
          </span v-else>
      </td>
      <td>
        <span v-if="showForm">
              <input  v-model="updatedHotel.email">
        </span>
        <span v-else>
            {{ hotel.email }}
          </span v-else>
      </td>
      <td> hotel.updateUser.username </td>
      <td>  {{ hotel.lastUpdated | shortMoment() }}
      </td>
      <td class="dropdown dropdown-table">
        <span v-if="showForm">
            <button v-on:click="save(updatedHotel)">Save</button>
        </span>
        <span v-else>
          <button v-on:click="editHotel(hotel)">Edit</button>
          <span class="hoverButton">
      <vue-dropdown :config="config"
                    @setSelectedOption="actionDropDown($event,hotel.id)"
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
   props: ['hotel', 'makes', 'models', 'drivers','reload','updatedResults'],
   data () {
        return {
          response: [],
          errors: [],
          //newHotel:{name:'',code:'',updateUser:{id:''}, phone:'',email:''},
          showHotel:null,
          updatedHotel:{},
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
         editHotel() {
          this.showForm=true;
          this.updatedHotel=this.hotel;
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
             return HotelService.fetchRoot(''+id)
               .then((res) => {
               if (res) {
                 console.log('RES: '+JSON.stringify(res));
               }
               if (res.data) {
               //this.showForm=false;
               console.log('show resData '+JSON.stringify(res.data))
               this.$emit('hotel-show', res.data);
               //showHotel=res.data;
               //showDialog=true
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


           if (event.value==='delete') {
             if (confirm('Delete record?')) {
               console.log('delete '+id)

               return HotelService.deleteNoCatch('', id)
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
                 //this.$emit('hotel-errors', error.response.data.error);

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
           const newName = this.updatedHotel;
           console.log('new Name =  '+JSON.stringify(newName))
           //+newName.id
           //this.newHotel.id=newName.id
           //this.newHotel.code=newName.code
           //this.newHotel.name=newName.name
           //this.newHotel.phone=newName.phone
           //this.newHotel.email=newName.email

           console.log('new newHotel =  '+JSON.stringify( newName))
           return HotelService.putRootNoCatch('/update/'+newName.id, newName)
             .then((res) => {
             if (res) {
                console.log('RES: '+JSON.stringify(res));
             }
             if (res.data||res.status===204) {
               this.showForm=false;
               console.log('resData '+JSON.stringify(res.data))
               this.$emit('hotel-update', res.data);
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
