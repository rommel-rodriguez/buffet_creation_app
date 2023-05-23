<nav id="sidebar">
    <br>
    <div class="temblor">
        <img src="assets/img/cacerola.png" alt="user" class="" width="200">
    </div>
    <ul class="list-unstyled components">
        <p>Bienvenido</p>
        <li class="mb-1">
            <a href="Controlador?menu=Home" class="link-light active" id="dashboard"> <i class="fas fa-home"></i><span class="nav_name"> Home</span> </a>
        </li>
        <p>Operaciones</p>
        <li class="mb-1">
            <a href="#compras" data-bs-toggle="collapse" aria-expanded="false"><i
                    class="fas fa-cash-register"></i> Presupuestos</a>
            <ul class="collapse list-unstyled" id="compras">
                <li>
                    <a href="#" class="link-light"> <i class="fas fa-fish"></i> <span class="nav_name">Organiza
                            tu men�</span> </a>
                </li>
                <li>
                    <a href="#" class="link-light"> <i class="fas fa-utensils"></i> <span class="nav_name">Tu men�
                            semanal</span> </a>
                </li>
                <li>
                    <a href="#" class="link-light"> <i class="fas fa-hand-holding-usd"></i> <span class="nav_name">Detalle de presupuestos</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="mb-1">
            <a href="#mantenimiento" data-bs-toggle="collapse" aria-expanded="false"><i
                    class="fas fa-user-cog"></i> Mantenimiento</a>
            <ul class="collapse list-unstyled" id="mantenimiento">
                <li>
                    <a href="categorias?menu=Categorias&accion=Listar" class="link-light"> <i class="fas fa-list-ol"></i> <span class="nav_name">Categor�as</span>
                    </a>
                </li>
                <li>
                    <a href="Controlador?menu=Comidas&accion=Listar" class="link-light"> <i class="fas fa-hamburger"></i> <span class="nav_name">Comidas</span> </a>
                </li>
                <li>
                    <a href="Controlador?menu=Insumos&accion=Listar" class="link-light"> <i class="fas fa-carrot"></i> <span class="nav_name">Insumos</span> </a>
                </li>
                <li>
                    <a href="Controlador?menu=Usuario&accion=Listar" class="link-light"> <i class="fas fa-users"></i> <span class="nav_name">Usuarios</span> </a>
                </li>
                <li>
                    <a href="Controlador?menu=Recetas&accion=Listar" class="link-light"> <i class="fas fa-users"></i> <span class="nav_name">Recetas</span> </a>
                </li>
            </ul>
        </li>
        <p>Sistema</p>
        <li class="mb-1">
            <a href="login.jsp" class="link-light active" id="dashboard"> <i class="fas fa-sign-out-alt"></i><span class="nav_name"> Cerrar sesi�n</span> </a>
        </li>
    </ul>
</nav>