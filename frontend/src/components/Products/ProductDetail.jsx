import { useParams } from "react-router-dom";
import { useFetchProductDetailById } from "../../hooks/useFetchProductDetailById";
import Spinner from "../Common/Spinner";
import { Card, CardContent, CardActions, Typography, Button } from '@mui/material';

export default function ProductDetail() {
  const { id } = useParams();


  const idProduct = Number(id);
  const {
    data: dataProduct,
    isLoading,
    isError,
    error,
  } = useFetchProductDetailById(idProduct);

  if (isLoading) {
    return (
      <div>
        <Spinner />
      </div>
    );
  } else if (isError) {
    return <div>Error: {error.message}</div>;
  }

  const product = dataProduct?.result;

  return (
    <Card>
      <CardContent>
        <Typography variant="h5" component="div">
          {product.nombre}
        </Typography>
        <Typography variant="body1" color="text.secondary">
          Código: {product.codigo}
        </Typography>
        <Typography variant="body1">
          Precio: ${product.precio}
        </Typography>
        <Typography variant="body1">
          Costo: ${product.costo}
        </Typography>
        <Typography variant="body1">
          Descripción: {product.descripcion || ''}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" variant="contained" color="primary" onClick={() => window.history.back()}>
          Volver
        </Button>
      </CardActions>
    </Card>
  );
}
