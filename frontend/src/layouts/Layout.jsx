import NavbarResponsive from '../components/Common/Navbar';
import '../styles/Layout.css';

// eslint-disable-next-line react/prop-types
const Layout =({children}) =>{
    return(
        <>
        <NavbarResponsive />
        <main>{children}</main>
        </>
    )
}

export default Layout;