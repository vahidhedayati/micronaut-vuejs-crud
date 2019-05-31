<template id="driverSelect-template" xmlns="http://www.w3.org/1999/xhtml">
  <div class="form-group"> <!--4-->
    <select class="form-control" v-model="selected" @change="updateValue()"> <!--7-->
      <option v-if="this.blankForm" disabled :value="null">Select a {{field}}</option> <!--2-->
      <!--1-->
      <option v-for="value in values" :value="value.id" :key="value.id">
        {{ value.name }}
      </option>
    </select>
  </div>
</template>

<script>
export default {
  // <1>
  props: {values: {type: Array}, field: {type: String},  actualItem: {  default: null },obj: {type: Object}},
  data: function () { // <2>
    return {
      //Set this value to be true if there is no item so it shows please select option
      blankForm: (this.actualItem==null),
      selected: this.actualItem
    }
  },
  created () {
    if (this.actualItem!=null) {
        console.log('doing stuff');
      this.updateValue();
    }
  },
  methods: { // <5>
    updateValue: function () { // <6>
      this.$emit('input', this.selected)
    }
  }
}
</script>

<style>
  /* Add custom rules here */
</style>
