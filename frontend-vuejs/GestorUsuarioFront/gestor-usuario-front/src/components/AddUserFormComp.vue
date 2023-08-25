<template>
    <div v-if="isOpen" class="modal" tabindex="-1">
      <div class="modal-dialog ">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Crear un Nuevo Usuario</h5>
            <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
          </div>
          <form @submit.prevent="submitForm">
            <div class="modal-body">
              <div class="mb-3">
                <label for="name" class="form-label">Nombre:</label>
                <input v-model="formData.name" type="text" id="name" class="form-control" required data-error="Este campo es obligatorio">
                <div class="invalid-feedback">Mensaje personalizado</div>
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input v-model="formData.email" type="email" id="email" class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="gender" class="form-label">Género:</label>
                <select v-model="formData.male" id="gender" class="form-select">
                  <option value="Masculino">Masculino</option>
                  <option value="Femenino">Femenino</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="profile-picture" class="form-label">Foto de Perfil:</label>
                <input v-model="formData.profilePicture" type="text" id="profilePicture" class="form-control" required>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" @click="closeModal">Cerrar</button>
              <button type="submit" class="btn btn-primary" :disabled="isSubmitDisabled">Enviar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <loadUser  ref="loadUser" @loadAllActiveUser="loadAllActiveUser" />
  </template>
  
  <script>
  import axios from 'axios';
  import loadUser from "@/views/UserManagerView.vue";
  export default {
    data() {
      return {
        formData: {
        name: null,
        email: null,
        male: null,
        profilePicture: null
      },
        isOpen: false,
        statusForm:true
      };
    },
    components: {
        loadUser
    },
    computed: {
    isSubmitDisabled() {
      return !this.formData.name || this.formData.name.length < 4 || !this.formData.email || this.formData.email.length < 4 || !this.formData.male 
              
    }
    },
    methods: {
      openModal() {
        this.isOpen = true;
      },
      closeModal() {
        this.isOpen = false;
      },
      submitForm() {
       
        axios.put('http://localhost:8080/api/addNewUser', this.formData) 
        .then(response => {
           alert(response.data)
           window.location.reload();
           this.closeModal()
        })
        .catch(error => {
          console.error('Error en la solicitud:', error);
          
        });


      },
      loadAllActiveUser(){
        this.$refs.loadUser.loadAllActiveUser()
      }

    }
  };
  </script>
  
  <style scoped>
  /* Estilos para el modal y el fondo oscuro */
  .modal {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
    font-family: Arial, Helvetica, sans-serif;
  }
  .modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    position: relative;
  }
  
  .close {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 20px;
    cursor: pointer;
  }

  .invalid-feedback {
  color: red;
}

/* Personalizar el estilo de validación */
.is-invalid {
  border-color: red;
}
  </style>
  