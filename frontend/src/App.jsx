import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Spinner from "./components/Common/Spinner"
import { Suspense, lazy } from "react"

const HomePage = lazy(() => import("./pages/HomePage"));
const ProductPage = lazy(() => import("./pages/ProductsPage"));

const ProductForm = lazy(() => import("./components/Products/ProductForm"));
const ProductDetail = lazy(() => import("./components/Products/ProductDetail"));


function App() {
  return (
    <>
      <Router>
      <Suspense fallback={<Spinner />}>
        <Routes>
          <Route
            path="/"
            element={
                <HomePage />
            }
          />
          <Route
            path="/productos"
            element={
                <ProductPage />
            }
          />
          <Route
            path="/productos/nuevo"
            element={
                <ProductForm />
            }
          />
          <Route
            path="/productos/detalle/:id"
            element={
              <ProductDetail />
            }
          />
        </Routes>
      </Suspense>
    </Router>
    </>
  )
}

export default App
