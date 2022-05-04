package controller;

import dao.TipoServicoDAOImplement;
import dao.PessoaDAOImplement;
import dao.UsuarioDAOImplement;
import dao.MetodoPagamentoDAOImplement;
import dao.CargoDAOImplement;
import dao.EnderecoDAOImplement;
import dao.MunicipioDAOImplement;
import dao.SolicitudeDAOImplement;
import dao.FuncionarioDAOImplement;
import dao.GeneroDAOImplement;
import dao.ClienteDAOImplement;
import dao.ServicoDAOImplement;
import dao.EstudoFactibilidadeDAOImplement;
import dao.BairroDAOImplement;
import dao.EstadoSolicitudeDAOImplement;
import dao.AcessoDAOImplement;
import dao.BussinessException;
import dao.ProvinciaDAOImplement;
import dao.IvaDAOImplement;
import dao.VendaDAOImplement;
import dao.SolicitudeServicoDAOImplement;

/**
 *
 * @author aaliagab generate
 */
public class Control {

    private TipoServicoDAOImplement TipoServicoDAO;
    private PessoaDAOImplement PessoaDAO;
    private UsuarioDAOImplement UsuarioDAO;
    private MetodoPagamentoDAOImplement MetodoPagamentoDAO;
    private CargoDAOImplement CargoDAO;
    private EnderecoDAOImplement EnderecoDAO;
    private MunicipioDAOImplement MunicipioDAO;
    private SolicitudeDAOImplement SolicitudeDAO;
    private FuncionarioDAOImplement FuncionarioDAO;
    private GeneroDAOImplement GeneroDAO;
    private ClienteDAOImplement ClienteDAO;
    private ServicoDAOImplement ServicoDAO;
    private EstudoFactibilidadeDAOImplement EstudoFactibilidadeDAO;
    private BairroDAOImplement BairroDAO;
    private EstadoSolicitudeDAOImplement EstadoSolicitudeDAO;
    private AcessoDAOImplement AcessoDAO;
    private ProvinciaDAOImplement ProvinciaDAO;
    private IvaDAOImplement IvaDAO;
    private VendaDAOImplement VendaDAO;
    private SolicitudeServicoDAOImplement SolicitudeServicoDAO;
    private Toast msg;

    public Control() {
        TipoServicoDAO = new TipoServicoDAOImplement();
        PessoaDAO = new PessoaDAOImplement();
        UsuarioDAO = new UsuarioDAOImplement();
        MetodoPagamentoDAO = new MetodoPagamentoDAOImplement();
        CargoDAO = new CargoDAOImplement();
        EnderecoDAO = new EnderecoDAOImplement();
        MunicipioDAO = new MunicipioDAOImplement();
        SolicitudeDAO = new SolicitudeDAOImplement();
        FuncionarioDAO = new FuncionarioDAOImplement();
        GeneroDAO = new GeneroDAOImplement();
        ClienteDAO = new ClienteDAOImplement();
        ServicoDAO = new ServicoDAOImplement();
        EstudoFactibilidadeDAO = new EstudoFactibilidadeDAOImplement();
        BairroDAO = new BairroDAOImplement();
        EstadoSolicitudeDAO = new EstadoSolicitudeDAOImplement();
        AcessoDAO = new AcessoDAOImplement();
        ProvinciaDAO = new ProvinciaDAOImplement();
        IvaDAO = new IvaDAOImplement();
        VendaDAO = new VendaDAOImplement();
        SolicitudeServicoDAO = new SolicitudeServicoDAOImplement();
    }

    public TipoServicoDAOImplement getTipoServicoDAO() {
        return TipoServicoDAO;
    }

    public PessoaDAOImplement getPessoaDAO() {
        return PessoaDAO;
    }

    public UsuarioDAOImplement getUsuarioDAO() {
        return UsuarioDAO;
    }

    public MetodoPagamentoDAOImplement getMetodoPagamentoDAO() {
        return MetodoPagamentoDAO;
    }

    public CargoDAOImplement getCargoDAO() {
        return CargoDAO;
    }

    public EnderecoDAOImplement getEnderecoDAO() {
        return EnderecoDAO;
    }

    public MunicipioDAOImplement getMunicipioDAO() {
        return MunicipioDAO;
    }

    public SolicitudeDAOImplement getSolicitudeDAO() {
        return SolicitudeDAO;
    }

    public FuncionarioDAOImplement getFuncionarioDAO() {
        return FuncionarioDAO;
    }

    public GeneroDAOImplement getGeneroDAO() {
        return GeneroDAO;
    }

    public ClienteDAOImplement getClienteDAO() {
        return ClienteDAO;
    }

    public ServicoDAOImplement getServicoDAO() {
        return ServicoDAO;
    }

    public EstudoFactibilidadeDAOImplement getEstudoFactibilidadeDAO() {
        return EstudoFactibilidadeDAO;
    }

    public BairroDAOImplement getBairroDAO() {
        return BairroDAO;
    }

    public EstadoSolicitudeDAOImplement getEstadoSolicitudeDAO() {
        return EstadoSolicitudeDAO;
    }

    public AcessoDAOImplement getAcessoDAO() {
        return AcessoDAO;
    }

    public ProvinciaDAOImplement getProvinciaDAO() {
        return ProvinciaDAO;
    }

    public IvaDAOImplement getIvaDAO() {
        return IvaDAO;
    }

    public VendaDAOImplement getVendaDAO() {
        return VendaDAO;
    }

    public SolicitudeServicoDAOImplement getSolicitudeServicoDAO() {
        return SolicitudeServicoDAO;
    }

    public Toast getMsg() {
        return msg;
    }

    public void messageFieldEmpty() {
        msg = new Toast("Você deve preencher todos os campos do formulário", 2000);
        msg.showToast();
    }

    public void messageErro(Exception e) {
        msg = new Toast(e.getMessage(), 2000);
        msg.showToast();
    }

    public void messageErroBussiness(BussinessException e) {
        msg = new Toast(e.getMessage(), 2000);
        msg.showToast();
    }

    public void messageNomeUsuario() {
        msg = new Toast("Você tem que escrever o nome de usuário", 2000);
        msg.showToast();
    }

    public void messageSenhaUsuario() {
        msg = new Toast("Você tem que escrever a senha de usuário", 2000);
        msg.showToast();
    }

    public void messageUsuarioNaoCorreto() {
        msg = new Toast("O username de usuário inserido não está correto", 2000);
        msg.showToast();
    }

    public void messageSenhaNaoCorreta() {
        msg = new Toast("A senha de usuário inserida não está correta", 2000);
        msg.showToast();
    }

    public void messageUmaLinha() {
        msg = new Toast("Você só pode selecionar uma linha", 2000);
        msg.showToast();
    }

    public void messageLinhasExcluir() {
        msg = new Toast("Você tem que fazer uma seleção das linhas a excluir", 2000);
        msg.showToast();
    }

    public void messageErroEmail() {
        msg = new Toast("O campo de email não é correto", 2000);
        msg.showToast();
    }

    public void messageSelecaoEditar() {
        msg = new Toast("Você tem que fazer uma seleção para editar", 2000);
        msg.showToast();
    }

    public void messageSelecaoRelatorio() {
        msg = new Toast("Você tem que fazer uma seleção para gerar relatório", 2000);
        msg.showToast();
    }

    public void messageOperacaoSucesso() {
        msg = new Toast("Operação feita com sucesso!!", 2000);
        msg.showToast();
    }
}
