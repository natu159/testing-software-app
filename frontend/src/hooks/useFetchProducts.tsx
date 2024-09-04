import { useQuery } from '@tanstack/react-query';
import { getProducts } from '../services/productService';

export const useFetchProducts = () => {
    const query = useQuery ({
        queryKey: ['dataProduct'],
        queryFn: () => getProducts()
    });
    
    return query;
}