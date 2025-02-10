const API_BASE_URL = "http://localhost:8080/api/carts";

// Obtiene referencia a los elementos HTML
const cartList = document.getElementById("cartList");
const createCartBtn = document.getElementById("createCartBtn");
const cartIdInput = document.getElementById("cartIdInput");
const getCartBtn = document.getElementById("getCartBtn");
const productDescInput = document.getElementById("productDescInput");
const productAmountInput = document.getElementById("productAmountInput");
const addProductBtn = document.getElementById("addProductBtn");
const deleteCartBtn = document.getElementById("deleteCartBtn");

// Almacena los IDs de los carritos creados
let cartIds = [];

// Función para crear un nuevo carrito
async function createCart() {
    try {
        const response = await fetch(`${API_BASE_URL}/new`, { method: "POST" });
        if (!response.ok) throw new Error("Error al crear el carrito");

        const cart = await response.json();
        cartIds.push(cart.id);
        updateCartList();
        alert(`Carrito creado con ID: ${cart.id}`);
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo crear el carrito");
    }
}

// Función para obtener la información de un carrito
async function getCart() {
    const cartId = cartIdInput.value;
    if (!cartId) return alert("Por favor, ingresa un ID de carrito");

    try {
        const response = await fetch(`${API_BASE_URL}/get/${cartId}`);
        if (!response.ok) throw new Error("Carrito no encontrado");

        const cart = await response.json();
        alert(`Carrito ID: ${cart.id}\nProductos: ${JSON.stringify(cart.products, null, 2)}`);
    } catch (error) {
        console.error("Error:", error);
        alert("Carrito no encontrado");
    }
}

// Función para agregar un producto a un carrito
async function addProductToCart() {
    const cartId = cartIdInput.value;
    const description = productDescInput.value;
    const amount = productAmountInput.value;

    if (!cartId || !description || !amount) {
        return alert("Por favor, completa todos los campos");
    }

    const product = {
        description,
        amount: parseInt(amount),
    };

    try {
        const response = await fetch(`${API_BASE_URL}/add/${cartId}/products`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(product),
        });

        if (!response.ok) throw new Error("Error al agregar el producto");

        alert("Producto agregado con éxito");
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo agregar el producto");
    }
}

// Función para eliminar un carrito
async function deleteCart() {
    const cartId = cartIdInput.value;
    if (!cartId) return alert("Por favor, ingresa un ID de carrito");

    try {
        const response = await fetch(`${API_BASE_URL}/delete/${cartId}`, { method: "DELETE" });
        if (!response.ok) throw new Error("Error al eliminar el carrito");

        cartIds = cartIds.filter(id => id !== cartId);
        updateCartList();
        alert("Carrito eliminado con éxito");
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo eliminar el carrito");
    }
}

// Función para actualizar la lista de carritos creados
function updateCartList() {
    cartList.innerHTML = "";
    cartIds.forEach(cartId => {
        const li = document.createElement("li");
        li.textContent = cartId;
        cartList.appendChild(li);
    });
}

// Asigna eventos a los botones
createCartBtn.addEventListener("click", createCart);
getCartBtn.addEventListener("click", getCart);
addProductBtn.addEventListener("click", addProductToCart);
deleteCartBtn.addEventListener("click", deleteCart);
