
import axios from 'axios'
const instance = axios.create({
  baseURL: `http://localhost:8080/`
})
export default {
  fetchParams (component,params) {
    return instance.get(component, {params:params })
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
    return instance.get(component,{responseType: 'blob', headers: { 'Accept': 'application/vnd.ms-excel' }})
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
    return instance.get(component)
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
    return instance.get(component)
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
    return instance.post(component, params)
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
    return instance.post(component, params)

  },
  createRootNoCatch (component, params) {
    return instance.post(component, params)

  },
  putRootNoCatch (component, params) {
    return instance.put(component, params)

  },
  patchRootNoCatch (component, params) {
    return instance.patch(component, params)

  },
  update(component,params) {

    return instance.patch(component, params)
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
    return instance.delete(component+'/' + id)
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
