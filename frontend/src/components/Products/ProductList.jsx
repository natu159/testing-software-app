import { DataGrid, GridActionsCellItem } from "@mui/x-data-grid";
import { useFetchProducts } from "../../hooks/useFetchProducts";
import Spinner from "../Common/Spinner";

import "../../styles/Products/ProductList.css";
import { SearchOutlined } from "@mui/icons-material";

export default function ProductList() {
  const { data: dataProduct, isLoading, isError, error } = useFetchProducts();

  if (isLoading) {
    return (
      <div>
        <Spinner />
      </div>
    );
  } else if (isError) {
    return <div>Error: {error?.message}</div>;
  }

  const rows = dataProduct?.result?.$values || [];

  const columns = [
    { field: "codigo", headerName: "Código", maxWidth: 200 },
    { field: "nombre", headerName: "Nombre", flex: 1 },
    { field: "precio", headerName: "Precio", maxWidth: 250 },
    { field: "costo", headerName: "Costo", maxWidth: 250 },
    {
      field: "actions",
      headerName: "Acciones",
      type: "actions",
      getActions: (params) => [
        <GridActionsCellItem
          key={`search-${params.row.id}`} // Adding a unique key prop
          icon={<SearchOutlined />}
          label=""
          onClick={() => {
            window.location.href = `/productos/detalle/${params.row.id}`;
          }}
        />,
      ],
    },
  ];

  return (
    <div className="table-section">
      <DataGrid
        rows={rows}
        columns={columns}
        getRowId={(row) => row.id}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
      />
    </div>
  );
}
