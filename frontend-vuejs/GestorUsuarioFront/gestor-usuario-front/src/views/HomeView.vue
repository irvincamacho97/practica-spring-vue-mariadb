<template>
    <nav class="navbar navbar-dark bg-dark nav-up">

        <label class="nav-menu" @click="managerUser()" >Gestor de  Usuarios</label>

        <label class="nav-menu" @click="msgSignOut()" title="Cerrar sesión"><i class="fas fa-sign-out"></i></label>
    
    </nav>
    <div class="fondo">

        <div class="container">
            <h1 class="h1">Bienvenido {{ user.name }}</h1>


        </div>
    </div>
</template>
<script>
import Swal from 'sweetalert2';
export default {
    data() {
        return {
            user: '',
        };
    },
    mounted() {

        const userData = localStorage.getItem('user');
        if (userData) {
            this.user = JSON.parse(userData);
            console.log(this.user);
        }

    },
    methods: {
        managerUser() {
            this.$router.push('/manager/user')
        },
        msgSignOut() {
            Swal.fire({
                title: '¿Deseas cerrar tu sesión?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#008000',
                cancelButtonColor: '#d33',
                cancelButtonText:'Cancelar',
                confirmButtonText: 'Si',
            }).then((result) => {
                if (result.isConfirmed) {
                    this.signOut()
                }
            })
        },
        signOut(){
            this.$store.commit('setUser', "")
            this.$router.push('/')
        }


    }
}
</script>
<style scoped>
.nav-up {
    margin-top: -60px;
    height: 70px;
}

.nav-menu {
    color: white;
    margin: 10px 45px;
    cursor: pointer;
}

.nav-menu:hover {
    transform: scale(1.7);
}

.fondo {
    position: relative;
    width: 100%;
    height: 100vh;
    background-image: url('@/assets/fondoHome.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    margin-top: -10px;
    background-color: rgba(0, 0, 0, 5.5);
    color: white;
    padding: 20px;
}

.container {
    height: 80vh;
    width: 80%;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.8);
    font-family: 'Proxima Nova', -apple-system, 'Helvetica Neue', Helvetica, Roboto, Arial, sans-serif;
    color: black;
    background-color: white;
    border-radius: 10px;
}
</style>