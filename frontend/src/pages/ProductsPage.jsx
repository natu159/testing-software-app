import Layout from "../layouts/Layout";
import ProductList from "../components/Products/ProductList";
import "../styles/Page.css";
import { Link } from "react-router-dom";

export default function ProductsPage() {
  return (
    <Layout>
      <div className="title-section">
        <h3>Lista de productos</h3>
        <Link className="btn btn-primary" to="/productos/nuevo">
          <span>Agregar</span>
        </Link>
      </div>
      <ProductList />
    </Layout>
  );
}
