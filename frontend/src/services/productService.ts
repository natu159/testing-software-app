import axios from "axios";

const apiURL = axios.create({
    baseURL: 'https://localhost:7107/api/'
});

export const getProductById = async (id: number) => {
    const res = await apiURL.get(`Producto/GetProducto/${id}`);
    return res.data;
};

export const getProducts= async () => {
    const res = await apiURL.get(`Producto/GetProductos`);
    return res.data;
};


