package com.youthchina.Qingyang;

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
public class HrTest {

    @Autowired
    private HrMapper hrMapper;

    @Test
    public void testGetHr() {
        Hr hr = hrMapper.selectHrById(1);
        Assert.assertEquals(Integer.valueOf(1), hr.getCompanyId());
    }

    @Test
    public void testGetHrByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Hr> hrs = hrMapper.selectHrByIdList(ids);
        Assert.assertEquals(2, hrs.size());
        for (Hr hr : hrs) {
            if (hr.getHrId() != 1 && hr.getHrId() != 2) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testHrInsert() {
        Hr hr = new Hr();
        hr.setCompanyId(1);
        hr.setHrOnJob(0);
        hr.setUserId(1);
        hrMapper.insertHr(hr);
    }

    @Test
    public void testUpdateHr() {
        Hr hr = hrMapper.selectHrById(2);
        hr.setCompanyId(2);
        hr.setHrOnJob(2);
        hr.setUserId(2);
        hrMapper.updateHr(hr);
    }

    @Test
    public void testDeleteHr() {
        hrMapper.deleteHr(2);
    }
}
*/
