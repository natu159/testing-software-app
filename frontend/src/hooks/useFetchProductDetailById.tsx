import { useQuery } from "@tanstack/react-query";
import { getProductById } from "../services/productService";

export const useFetchProductDetailById = (id: number) => {
  const query = useQuery({
    queryKey: ["dataProduct"],
    queryFn: () => getProductById(id),
  });

  return query;
};
