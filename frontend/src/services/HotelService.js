import Api from '@/services/Api'
import axios from 'axios'
const instance = axios.create({
  baseURL: `http://localhost:8080/`
})
export default {
  fetchParams (component,params) {
    return Api().get(component, {params:params })
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  },

  fetchBlob (component) {
    return Api().get(component,{responseType: 'blob', headers: { 'Accept': 'application/vnd.ms-excel' }})
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  },
  fetchName (component) {
    return Api().get(component)
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  },
  fetchRoot (component) {
    return  Api().get(component)
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  },
  createName (component, params) {
    return Api().post(component, params)
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  },
  createNoCatch (component, params) {
    return Api().post(component, params)

  },
  createRootNoCatch (component, params) {
    return  Api().post(component, params)

  },
  putRootNoCatch (component, params) {
    return  Api().put(component, params)

  },
  patchRootNoCatch (component, params) {
    return Api().patch(component, params)

  },
  update(component,params) {

    return Api().patch(component, params)
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  },
  deleteNoCatch (component, id) {
    return instance.delete(component+'/' + id)
  },
  delete (component,id) {
    return Api().delete(component+'/' + id)
      .catch((error) => {
      if (error.response) {
      console.log(error.response);
    } else if (error.request) {
      console.log(error.request);
    } else {
      console.log('Error', error.message);
    }
  });
  }
}
