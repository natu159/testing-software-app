import { Spinner as BootstrapSpinner } from "react-bootstrap";

export default function Spinner() {
  return (
    <div className="text-center vh-100 d-flex">
      <BootstrapSpinner animation="border" role="status" className="m-auto">
        <span className="visually-hidden">Loading...</span>
      </BootstrapSpinner>
    </div>
  );
}
