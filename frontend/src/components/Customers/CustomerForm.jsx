import { useFormik } from "formik";
import * as yup from "yup";
import Button from "@mui/material/Button"; // Asegúrate de que la versión de Material-UI sea compatible
import TextField from "@mui/material/TextField";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Layout from "../../layouts/Layout";

// Definir el esquema de validación
const validationSchema = yup.object({
  codigo: yup
    .string()
    .min(1, "Código es requerido")
    .required("Código es requerido"),
  nombre: yup
    .string()
    .min(1, "Nombre es requerido")
    .required("Nombre es requerido"),
  descripcion: yup.string().nullable(),
  precio: yup
    .number()
    .required("Precio es requerido")
    .typeError("Precio debe ser un número"),
  costo: yup
    .number()
    .required("Costo es requerido")
    .typeError("Costo debe ser un número"),
});


export default function ProductForm() {
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      codigo: "",
      nombre: "",
      descripcion: "",
      precio: 0,
      costo: 0,
    },
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      try {
        await axios.post(
          "https://localhost:7107/api/Producto/CreateProducto",
          values
        );
        alert("Producto registrado exitosamente");
        navigate("/productos");
      } catch (error) {
        console.error("Error al registrar el producto", error);
        alert("Error al registrar el producto");
      }
    },
  });

  return (
    <Layout>
      <div className="title-section">
        <h3>Agregar producto</h3>
        <Link className="btn btn-primary" to="/productos/">
          Volver
        </Link>
      </div>
      <form onSubmit={formik.handleSubmit}>
        <TextField
          fullWidth
          id="codigo"
          name="codigo"
          label="Código"
          value={formik.values.codigo}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.codigo && Boolean(formik.errors.codigo)}
          helperText={formik.touched.codigo && formik.errors.codigo}
          margin="normal"
        />
        <TextField
          fullWidth
          id="nombre"
          name="nombre"
          label="Nombre"
          value={formik.values.nombre}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.nombre && Boolean(formik.errors.nombre)}
          helperText={formik.touched.nombre && formik.errors.nombre}
          margin="normal"
        />
        <TextField
          fullWidth
          id="descripcion"
          name="descripcion"
          label="Descripción"
          value={formik.values.descripcion}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={
            formik.touched.descripcion && Boolean(formik.errors.descripcion)
          }
          helperText={formik.touched.descripcion && formik.errors.descripcion}
          margin="normal"
          multiline
          rows={4}
        />
        <TextField
          fullWidth
          id="precio"
          name="precio"
          label="Precio"
          type="number"
          value={formik.values.precio}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.precio && Boolean(formik.errors.precio)}
          helperText={formik.touched.precio && formik.errors.precio}
          margin="normal"
        />
        <TextField
          fullWidth
          id="costo"
          name="costo"
          label="Costo"
          type="number"
          value={formik.values.costo}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.costo && Boolean(formik.errors.costo)}
          helperText={formik.touched.costo && formik.errors.costo}
          margin="normal"
        />
        <Button
          color="primary"
          variant="contained"
          fullWidth
          type="submit"
          sx={{ mt: 2 }}
        >
          Registrar Producto
        </Button>
      </form>
    </Layout>
  );
}
