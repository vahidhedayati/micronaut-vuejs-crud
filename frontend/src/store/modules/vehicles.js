import axios from 'axios'
const instance = axios.create({
  baseURL: `http://localhost:8080/`
})
const state = {
    loadVehicles: {},
    params:{},
    total: 0,
    numberOfPages: 0

};
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
const getters = {
    loadVehicles() {
      console.log('ahhh '+state.loadVehicles)
      return state.loadVehicles;
    },
    total: state => state.total,
    numberOfPages: state => state.numberOfPages
};
/**
 * This is called through Rental.vue - when initialiseVehicle is called
 * it passes the params over to this action - also appended or defined in mutation below...
 * The output is then stored in this vuex object - called vehicles -
 * vehicles its total and number of pages become a vuex session contained list
 * each page change will update all these cache values
 * @type {boolean}
 */
const actions = {
    initVehicles: ({commit}, params) => {
      console.log("params = "+params.params)
    instance.get('/guest/rental?'+params.params)
      .then((response) =>{
        console.log('DATA '+JSON.stringify(response.data.instanceList));
        //commit('SET_STORE', response.data.instanceList,response.data.instanceTotal,response.data.numberOfPages);
        //commit('SET_TOTAL', response.data.instanceTotal);
        //commit('SET_PAGES', response.data.numberOfPages);
        state.loadVehicles = response.data.instanceList;
        state.total = response.data.instanceTotal;
        state.numberOfPages = response.data.numberOfPages;

        console.log(' -STATE 00>> '+JSON.stringify(state.vehicles))
      });
    },
  updateVehicles:  ({commit}, vehicleObject) => {
  console.log('updatting '+vehicleObject.vehicle.id)
  state.loadVehicles=updateObjectsInArr(state.loadVehicles, [vehicleObject.vehicle])


  }


};

const mutations = {
    'SET_STORE' (state,vehicles,total,numberOfPages) {
      console.log('sseting up vehicles as '+JSON.stringify(vehicles))
      state.loadVehicles = vehicles;
      state.total = total;
      state.numberOfPages = numberOfPages;
    },
 // 'SET_TOTAL' (state, total) {
  //  state.total = total;
  //},
 // 'SET_PAGES' (state, numberOfPages) {
  //  state.numberOfPages = numberOfPages;
  //},

};

export default {
    state,
    getters,
    actions,
  mutations: {
    initVehicles(state, { params }) {
      localStorage.setItem('params', params)

    }
  }
}
