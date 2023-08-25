import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
        user: null
    };
  },
  mutations: {
    setUser(state,user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user)); // Almacena los datos en el localStorage
    },
    CLEAR_USER(state) {
      state.user = null;
      localStorage.removeItem('user'); // Elimina los datos del localStorage
    }
  },
  actions: {
    setUser(state, userData) {
        state.user = userData;
      }
  },
  getters: {
    getUser(state) {
        return state.user;
      }
  },
  modules: {
    // Tus módulos aquí (si los tienes)
  }
});

export default store;
