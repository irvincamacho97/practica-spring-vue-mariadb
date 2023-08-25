<template>
    <div v-if="isOpen" class="modal" tabindex="-1">
        <div class="modal-dialog ">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Editar usuario {{ this.idEditable }}</h5>
                    <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
                </div>
                <form @submit.prevent="submitForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="name" class="form-label">Nombre:</label>
                            <input type="text" v-model="datos.name" id="name" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" v-model="datos.email" id="email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Género:</label>
                            <select id="gender" class="form-select">
                                <option value="Masculino">Masculino</option>
                                <option value="Femenino">Femenino</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="profile-picture" class="form-label">Foto de Perfil:</label>
                            <input type="text" v-model="datos.profilePicture" id="profilePicture" class="form-control" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" @click="closeModal">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </div>
                    <loadUser ref="loadUserRef" />
                </form>
            </div>
        </div>
    </div>
   
</template>
  
<script>
import axios from 'axios';
import loadUser from "@/views/UserManagerView.vue";

export default {
    data() {
        return {
            isOpen: false,
            datos: '',
            datosUpdate:''
        };
    },

    props: {
        idEditable: null,
    },
    components: {
        loadUser
    },
    watch: {
        idEditable: function (newVal) {
            if (newVal) {
                this.loadPageOnIdUser(newVal);
            }
        },
    },
    methods: {
        loadPageOnIdUser(idUser) {
            axios.get('http://localhost:8080/api/getActiveUser/' + idUser)
                .then(response => {
                    this.datos= response.data
                })
                .catch(error => {
                    console.log(error.message)
                });
        },
        openModal() {
            this.isOpen = true;
        },
        closeModal() {
            this.isOpen = false;
            
        },
        submitForm() {
            axios.post('http://localhost:8080/api/updateActiveUser', this.datos)
                .then(response => {
                   
                  alert(response.data);
                  window.location.reload();
                                  
                })
                .catch(error => {
                    console.error('Error en la solicitud:', error);

                });
                this.closeModal()
        },
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
</style>
  