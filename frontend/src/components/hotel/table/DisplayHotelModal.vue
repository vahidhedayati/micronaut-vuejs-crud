<template>
  <modal :show="show" @close="close"  v-if="show">
    <div class="modal-header">
      <h3>Display hotel {{hotel.name}}</h3>
    </div>
    <div class="modal-body">
      <div class="row">
      <div class="form-label col-sm-12">
        Name
        {{hotel.name}}
      </div>

      <div class="form-label col-sm-12">
        code
        {{hotel.code}}
      </div>

      <div class="form-label col-sm-12">
        username
        hotel.updateUser.username
      </div>
      <div class="form-label col-sm-12">
        last updated
        {{ hotel.lastUpdated | shortMoment() }}
      </div>
        <div class="form-label col-sm-12">
          Rooms
          <ul id="example-1">
            <li v-for="item in hotel.hotelRooms">
              roomType: {{ item.roomType}}
              price:  {{item.price}}
              stock:  {{item.stockTotal}}
            </li>
          </ul>
        </div>
    </div>
      <div class="modal-footer text-right">
           <button class="modal-default-button" @click="close()">
             Close
           </button>
      </div>
    </div>
  </modal>
</template>

<script>
  import Datepicker from 'vuejs-datepicker';
  import modal from '../../Modal'
  import moment from 'moment';
  export default {
    props: ['show','hotel' ],

    data: function () {
      return {
        errors:[],
      };
    },
    components: {
      modal,
      Datepicker,
      moment
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
      close: function () {
        console.log('running close')
        this.$emit('close');
      }
    }
  }

</script>
<style>
  .dateField input {
    color: red;
    max-width:90px !important;
  }
</style>
