<template>
    <div class="fondo">
        <div class="container">
            <div class="row gutter">
                <div class="col">
                    <h1 class="h1">Inicio de sesión</h1>
                    <form @submit.prevent="login">
                        <table class="table">
                            <tr>
                                <td class="table-spacing">
                                    <label class="td-top">Correo:</label>
                                </td>

                                <td class="table-spacing">
                                    <input v-model="email" type="text" class="form-control form-control input-type"
                                        placeholder="Ingrese Correo">
                                    <small v-if="statusForm"
                                        class="text-danger msg-error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Formato
                                        de email
                                        inválido.</small>
                                </td>

                            </tr>
                        </table>
                        <button class="btn btn-outline-success button-all" @click="login"
                            :disabled="isSubmitDisabled">Ingresar</button>
                    </form>
                </div>

            </div>

        </div>

    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            email: '',
            statusForm: false,
            msgError: '',
        };
    },
    computed: {
        isSubmitDisabled() {
            return !this.isValidEmail(this.email)
        }
    },
    methods: {
        login() {

            this.statusForm = false;
            axios.post('http://localhost:8080/api/loginUser/' + this.email)
                .then(response => {

                    this.$store.commit('setUser', response.data); 

                    if (this.$store.state.user !== "") {
                        this.$router.push('/home')
                    } else {
                        alert("Usuario no encontrado")
                    }
                })
                .catch(error => {
                    console.log(error.message);
                });

        },
        isValidEmail(email) {
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const isValid = emailPattern.test(email);
            if (isValid != true) {
                this.statusForm = true
            } else {
                this.statusForm = false
            }
            if (isValid != true && this.email === '') {
                this.statusForm = false
            }
            return isValid;
        },

    }
};
</script>


<style scoped>
.table {
    width: 100%;
    border-collapse: collapse;
    border: 1px solid white;
    margin-left: 100px;

}

.fondo {
    position: relative;
    width: 100%;
    height: 100vh;
    background-image: url('@/assets/fondo.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    margin-top: -60px;
    background-color: rgba(0, 0, 0, 5.5);
    color: white;
    padding: 20px;
}

.container {
    height: 80vh;
    width: 50%;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.8);
    font-family: 'Proxima Nova', -apple-system, 'Helvetica Neue', Helvetica, Roboto, Arial, sans-serif;
    color: black;
    background-color: white;
    border-radius: 10px;

}

.h1 {
    font-size: 44px;
    margin-bottom: 20px;
    color: black;
    font-family: 'Proxima Nova', -apple-system, 'Helvetica Neue', Helvetica, Roboto, Arial, sans-serif;
}

.msg-error {
    font-size: 15px;
}

.input-type {
    width: 50%;
    margin: 10px;
    border: 1px solid yellow;
    padding: 5px;
}

.td-top {
    margin-top: 16px;
    color: black;
    font-family: 'Proxima Nova', -apple-system, 'Helvetica Neue', Helvetica, Roboto, Arial, sans-serif;
}

.table-spacing {
    padding: 5px;
}
</style>