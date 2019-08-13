package com.thecodeside.mvvmca.features.drivers.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thecodeside.mvvmca.helpers.RxTestRule
import com.thecodeside.mvvmca.network.ApiInterface
import org.junit.Rule
import org.mockito.Mock

/*
@RunWith(PowerMockRunner::class)
@PrepareForTest(ReactiveLocationProvider::class)*/
class GetDriversListTest {

    @Mock
    private lateinit var api: ApiInterface

    private lateinit var getDriversList: GetDriversList

    /*  @Mock
      private lateinit var reactiveLocationProvider: ReactiveLocationProvider
  */
    @Rule
    @JvmField
    var rxTestRule: RxTestRule = RxTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    /* @Before
     fun setUp() {
         getDriversList = GetDriversList(reactiveLocationProvider, api)
     }*/

    /* @Test
     fun `when getDriversList is invoked it collect drivers list from api api`() {
         getDriversList().test()
         verify(api, times(1)).getCoordinates(any())
     }*/
}