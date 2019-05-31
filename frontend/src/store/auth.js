const auth = {
  namespaced: true,
  state: {
    isAuthenticated: false
  },
  mutations: {
    isAuthenticated (state, payload) {
      state.isAuthenticated = payload
    }
  },
  actions: {
    login ({commit}, payload) {
      console.log("auth action"+ payload);
      commit('isAuthenticated', payload)
    },
    logout ({commit}) {
      commit('isAuthenticated', false)
    }
  },
  getters: {
    isAuthenticated (state) {
      console.log("authenticated ???? "+state.isAuthenticated)
      return  state.isAuthenticated
    }
  }
}

export default auth
