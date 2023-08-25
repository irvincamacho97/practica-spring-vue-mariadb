<template>
        <nav class="navbar navbar-dark bg-dark nav-up">

<label class="nav-menu" @click="dataUser()" >Mis Datos</label>

<label class="nav-menu" @click="msgSignOut()" title="Cerrar sesión"><i class="fas fa-sign-out"></i></label>

</nav>
    <div class="fondo">

        <div class="container">
            <h1 class="h1">Usuarios</h1>
            <table id="tabla" class="table">
                <thead>
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Email</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Estatus</th>
                        <th scope="col" colspan="2">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(user) in displayedUsers" :key="user">
                        <td>{{ user.name }}</td>
                        <td>{{ user.email }}</td>
                        <td>{{ user.male }}</td>
                        <td>{{ user.status }}</td>
                        <td @click="openModalEdit(user.idUser)"><i class="fas fa-pencil-alt edit-boton"></i></td>
                        <td @click="msgConfirmDelete(user.idUser)"><i class="fas fa-trash-alt delete-boton"></i></td>
                    </tr>
                    <tr>
                        <AddUserFormComp ref="AddUserFormComp" @closeModal="closeModal" />
                        <editUserFormCom ref="editUserFormCom" :idEditable=idUserEditable @closeModal="closeModal" />
                    </tr>
                </tbody>
            </table>
            <button class="btn btn-outline-success button-all" @click="openModal">Crear usuario</button>
        </div>
        <nav aria-label="Page navigation" class="pagination-nav">
            <ul class="pagination">
                <li class="page-item" v-for="pageNumber in totalPages" :key="pageNumber">
                    <a class="page-link" @click="changePage(pageNumber)">{{ pageNumber }}</a>
                </li>
            </ul>
        </nav>
    </div>
</template>
  
<script>
import axios from 'axios';
import AddUserFormComp from "@/components/AddUserFormComp.vue";
import editUserFormCom from "@/components/editUserFormCom.vue";
import Swal from 'sweetalert2';

export default {
    data() {
        return {
            datos: '',
            showModal: false,
            currentPage: 1,
            pageSize: 6,
            idUserEditable: ''
        };
    },
    mounted() {
        this.loadAllActiveUser()
    },
    computed: {
        totalPages() {
            return Math.ceil(this.datos.length / this.pageSize);
        },
        displayedUsers() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            return this.datos.slice(startIndex, endIndex);
        },
    },
    components: {
        AddUserFormComp,
        editUserFormCom
    },
    methods: {
        loadAllActiveUser() {
            axios.get('http://localhost:8080/api/getAllActiveUser')
                .then(response => {
                    this.datos = response.data
                })
                .catch(error => {
                    console.log(error.message)
                });
        },

        openModal() {
            this.$refs.AddUserFormComp.openModal()
        },
        changePage(pageNumber) {
            this.currentPage = pageNumber;
        },
        msgDelete(idUser) {


            this.deleteUser(idUser)
        },
        deleteUser(idUser) {
            axios.delete('http://localhost:8080/api/deleteUser/' + idUser)
            .then(response => {
                this.loadAllActiveUser()
                console.log(response.data);
                })
                .catch(error => {
                    console.log(error.message)
                });
                         
        },
        openModalEdit(idUser) {
            this.idUserEditable = idUser
            this.$refs.editUserFormCom.openModal()

        },

        msgConfirmDelete(idUser) {
            Swal.fire({
                title: '¿Deseas eliminar este usuario?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#008000',
                cancelButtonColor: '#d33',
                cancelButtonText:'Cancelar',
                confirmButtonText: 'Eliminar',
            }).then((result) => {
                if (result.isConfirmed) {
                    this.deleteUser(idUser)
                    Swal.fire(
                        'Usuario Eliminado!',
                    )
                }
            })
        },
        dataUser() {
            this.$router.push('/home')
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
};
</script>
  
<style scoped>
.container {

    height: 80vh;
    width: 80%;
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

.table {
    width: 100%;
    border-collapse: collapse;
    border: 1px solid white;
}

.table th,
.table td {
    padding: 10px;
    border: 1px solid white;
}

.button-all {
    border-radius: 10px;
    position: fixed;
    bottom: 160px; /* Ajusta el valor para la distancia desde el borde inferior */
    right: 200px;
}

.pagination-nav {
    display: flex;
    justify-content: center;
    margin-top: -60px;

}

.page-link {
    padding: 5px 10px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-decoration: none;
    color: #333;
    cursor: pointer;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
}

.page-link:hover {
    background-color: #ddd;
    color: #000;
    /* Cambia el color del texto al pasar el mouse */
}

.edit-boton {

    color: green
}

.delete-boton {
    color: red
}

.edit-boton:hover {
    transform: scale(2.1);
    color: green;

}

.delete-boton:hover {
    transform: scale(2.1);
    color: red;

}

.fondo {
    position: relative;
    width: 100%;
    height: 100vh;
    background-image: url('@/assets/fondoHome.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    margin-top: -60px;
    background-color: rgba(0, 0, 0, 5.5);
    color: white;
    padding: 20px;
}

.title {
    margin-top: -390px;
    right: -90px;

}
.nav-up {
    margin-top: -110px;
    height: 170px;
}

.nav-menu {
    color: white;
    margin: 0px 45px;
    cursor: pointer;
}

.nav-menu:hover {
    transform: scale(1.7);
}

</style>
