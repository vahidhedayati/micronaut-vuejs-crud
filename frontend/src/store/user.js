
const user = {
  namespaced: true,
  state: {
    data: '',
    userLogged: false,
    token: '',
    profile: ''
  },
  mutations: {
    allUsers (state, payload) {
      state.users = payload
    },
    userLogged  (state, payload) {
      state.data = payload
      state.userLogged = true
    },
    setToken (state, payload) {
      state.token = payload
    },
    setProfile (state, payload) {
      state.profile = payload
    },
    deleteUser (state, payload) {
      state.users = state.users.filter((user) => {
        return user._id !== payload
      })
    },
    logout (state, payload) {
      state.token = ''
      state.data = ''
      state.userLogged = false
      state.profile = ''
    }
  },
  actions: {
    userLogged ({commit}, payload) {
      commit('userLogged', payload)
    },
    setToken ({commit}, payload) {
      commit('setToken', payload)
    },
    setProfile ({commit}, payload) {
      commit('setProfile', payload)
    },
    allUsers ({commit}, payload) {
      commit('allUsers', payload)
    },
    deleteUser ({commit}, payload) {
      commit('deleteUser', payload)
    },
    logout ({commit}) {
      commit('logout')
    }
  },
  getters: {}
}

export default user
