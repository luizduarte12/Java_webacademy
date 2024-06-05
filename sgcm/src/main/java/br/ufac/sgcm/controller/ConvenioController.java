package br.ufac.sgcm.controller;


import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.dao.ConvenioDao;
import br.ufac.sgcm.model.Convenio;
import br.ufac.sgcm.model.Especialidade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class ConvenioController implements IController<Convenio> {
        private ConvenioDao cDao;

        public ConvenioController(){
            cDao = new ConvenioDao();
        }

        @Override
        public int delete(Convenio objeto) {
            int registrosAfetados = cDao.delete(objeto);
            return registrosAfetados;
        }

        @Override
        public List<Convenio> get() {
            List<Convenio> registros = cDao.get();
            return registros;
        }

        @Override
        public Convenio get(Long id) {
            Convenio registro = cDao.get(id);
            return registro;
        }

        @Override
        public List<Convenio> get(String termoBusca) {
            List<Convenio> registros = cDao.get(termoBusca);
            return registros;
        }

        @Override
        public Convenio processFormRequest(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
                Convenio item = new Convenio();
                String submit = request.getParameter("submit");
                String paramId = request.getParameter("id");
                if (paramId != null && !paramId.isEmpty()) {
                    Long id = Long.parseLong(paramId);
                    item = this.get(id); 

                if (submit != null) {
                    item.setNome(request.getParameter("nome"));
                    this.save(item);
        
                    try { 
                        response.sendRedirect("especialidades.jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
                return item;
            }

        @Override
        public List<Convenio> processListRequest(HttpServletRequest request) {
            List<Convenio> registros = new ArrayList<>();
            String paramExcluir = request.getParameter("excluir");
            if (paramExcluir != null && !paramExcluir.isEmpty()) {
                Convenio con= new Convenio();
                Long id = Long.parseLong(paramExcluir);
                con = this.get(id);
                this.delete(con);
            }
            registros = this.get();
            return registros;
        }

        @Override
        public int save(Convenio objeto) {
            int registrosAfetados = 0;
            if (objeto.getId() == null){
                registrosAfetados = cDao.insert(objeto);
            }
            else{
                registrosAfetados = cDao.update(objeto);
            }
                
            return registrosAfetados;
        }
    

}
